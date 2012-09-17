package knowledge.survey.util;

import java.util.Comparator;

import knowledge.survey.oxm.Result;

public class ResultComparator implements Comparator<Result> {

	@Override
	public int compare(Result r1, Result r2) {
		if(r1.getScore()>=r2.getScore())
		    return -1;
		else
			return 1;
	}

}
