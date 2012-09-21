//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.21 at 09:02:17 AM CEST 
//


package knowledge.survey.oxm;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for part.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="part">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="第一部分：收集相关材料的情况"/>
 *     &lt;enumeration value="第二部分：实验性工作的相关情况"/>
 *     &lt;enumeration value="第三部分：讨论与交流想法的情况"/>
 *     &lt;enumeration value="第四部分：提出创新性想法的情况"/>
 *     &lt;enumeration value="第五部分：规划研究的情况"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "part")
@XmlEnum
public enum Part {

    @XmlEnumValue("\u7b2c\u4e00\u90e8\u5206\uff1a\u6536\u96c6\u76f8\u5173\u6750\u6599\u7684\u60c5\u51b5")
    第一部分_收集相关材料的情况("\u7b2c\u4e00\u90e8\u5206\uff1a\u6536\u96c6\u76f8\u5173\u6750\u6599\u7684\u60c5\u51b5"),
    @XmlEnumValue("\u7b2c\u4e8c\u90e8\u5206\uff1a\u5b9e\u9a8c\u6027\u5de5\u4f5c\u7684\u76f8\u5173\u60c5\u51b5")
    第二部分_实验性工作的相关情况("\u7b2c\u4e8c\u90e8\u5206\uff1a\u5b9e\u9a8c\u6027\u5de5\u4f5c\u7684\u76f8\u5173\u60c5\u51b5"),
    @XmlEnumValue("\u7b2c\u4e09\u90e8\u5206\uff1a\u8ba8\u8bba\u4e0e\u4ea4\u6d41\u60f3\u6cd5\u7684\u60c5\u51b5")
    第三部分_讨论与交流想法的情况("\u7b2c\u4e09\u90e8\u5206\uff1a\u8ba8\u8bba\u4e0e\u4ea4\u6d41\u60f3\u6cd5\u7684\u60c5\u51b5"),
    @XmlEnumValue("\u7b2c\u56db\u90e8\u5206\uff1a\u63d0\u51fa\u521b\u65b0\u6027\u60f3\u6cd5\u7684\u60c5\u51b5")
    第四部分_提出创新性想法的情况("\u7b2c\u56db\u90e8\u5206\uff1a\u63d0\u51fa\u521b\u65b0\u6027\u60f3\u6cd5\u7684\u60c5\u51b5"),
    @XmlEnumValue("\u7b2c\u4e94\u90e8\u5206\uff1a\u89c4\u5212\u7814\u7a76\u7684\u60c5\u51b5")
    第五部分_规划研究的情况("\u7b2c\u4e94\u90e8\u5206\uff1a\u89c4\u5212\u7814\u7a76\u7684\u60c5\u51b5");
    private final String value;

    Part(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Part fromValue(String v) {
        for (Part c: Part.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
