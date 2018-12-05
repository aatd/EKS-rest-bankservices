package aufgabenblatt_2_RESTFul_API;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "transaktionsTyp")
@XmlEnum
public enum TransaktionsTyp 
{
    @XmlEnumValue(value = "einzahlung")
	EINZAHLUNG,
    @XmlEnumValue(value = "auszahlung")
	AUSZAHLUNG
}
