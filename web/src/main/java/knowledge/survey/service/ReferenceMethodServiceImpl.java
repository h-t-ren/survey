package knowledge.survey.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import knowledge.survey.oxm.ItemResult;
import knowledge.survey.oxm.Method;
import knowledge.survey.oxm.Preference;
import knowledge.survey.oxm.QuestionType;
import knowledge.survey.oxm.Result;
import knowledge.survey.oxm.Results;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("referenceMethodService")
public class ReferenceMethodServiceImpl implements ReferenceMethodService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private final int k=2;
	@Autowired private ResultsService resultsService;

	@Override
	public void solve(Preference preference, String dataSet) throws IOException {
	
		float epsilon = preference.getParameter();
		Method method = preference.getMethod();
		List<Integer> referencePoints = preference.getReferencePoint();
		Results results =resultsService.getResults(dataSet);
		if(method.name().equals(Method.BASIC_RFP.name()))
		{
			basicReferencePointMethod(epsilon,referencePoints,results);
		}
		else
		{
			enhancedReferencePointMethod(epsilon,referencePoints,results);
		}
		resultsService.storeResults(results, dataSet);
		
	}
	
	private void basicReferencePointMethod(float epsilon, List<Integer> referencePoints,Results results)
	{
		for(Result result: results.getResult())
		{
			
			if(result.getQuestion().getQuestionType().equals(QuestionType.控制性))
				continue;
			boolean reversed = (result.getQuestion().isReversed()!=null&&result.getQuestion().isReversed()==true)?true:false;
			List<ItemResult> itemResults = result.getItemResult();
		    List<Integer> y = new ArrayList<Integer>();
			if(reversed)
			{
				for(ItemResult itemResult:itemResults)
				{
					y.add(0,itemResult.getPercentage());
				}
			}
			else
			{
				for(ItemResult itemResult:itemResults)
				{
					y.add(itemResult.getPercentage());
				}
			}
		
			int min=10000;
			int sum=0;
			for(int i=1; i<=5;i++)
			{
				int achievement= (y.get(i-1)-referencePoints.get(i-1))*sign(i,k);
				if(achievement<min) min =achievement;
				sum=sum+achievement;
			}
			float score = min+epsilon*sum;
		
			result.setScore(score);
		//	log.debug("store: " +score);
		}
	}
	private void enhancedReferencePointMethod(float epsilon, List<Integer> referencePoints,Results results)
	{
		float delta= 1+1/epsilon;
	//	log.debug("delta:" + delta);
		float parameter = 2/(k*(1+delta));
	//	log.debug("parameter:" + parameter);
		for(Result result: results.getResult())
		{
			
			if(result.getQuestion().getQuestionType().equals(QuestionType.控制性))
				continue;
			boolean reversed = (result.getQuestion().isReversed()!=null&&result.getQuestion().isReversed()==true)?true:false;
			List<ItemResult> itemResults = result.getItemResult();
		    List<Integer> y = new ArrayList<Integer>();
			if(reversed)
			{
				for(ItemResult itemResult:itemResults)
				{
					y.add(0,itemResult.getPercentage());
				}
			}
			else
			{
				for(ItemResult itemResult:itemResults)
				{
					y.add(itemResult.getPercentage());
				}
			}
		
			int sumright=0;
			int sumLeft=0;
			for(int i=1; i<=5;i++)
			{
				int achievement= (y.get(i-1)-referencePoints.get(i-1))*sign(i,k);
				int achiLeft=0,achiRight=0;
				if(achievement>0) 
					achiLeft=achievement;
				else
					achiRight=achievement;
				sumLeft=sumLeft+achiLeft;
				sumright=sumright+achiRight;
			}
			float score = parameter*sumLeft+delta*sumright;
			result.setScore(score);
		//	log.debug("store: " +score);
		}
	}
	

	private int sign(int i, int k)
	{
		if(i<=k)
			return 1;
		else
			return -1;
	}

}
