package knowledge.survey.util;

public enum FileNameEnum {
	profile,
	questions,
	responses,
	全部,
    化工学院,
    机械与动力工程学院,
    商学院,
    资源与环境工程学院,
    硕士研究生,
    博士研究生,
    博士生导师,
    讲师,
    硕士生导师;

    public String value() {
        return name();
    }

    public static FileNameEnum fromValue(String v) {
        return valueOf(v);
    }


}
