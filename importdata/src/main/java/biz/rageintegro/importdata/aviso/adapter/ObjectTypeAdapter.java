package biz.rageintegro.importdata.aviso.adapter;

import biz.rageintegro.importdata.aviso.dto.ObjectType;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class ObjectTypeAdapter {
    private ObjectType getTypeFromTitle(String title) {
        if (title == null) {
            return null;
        }
        if (title.contains("Квартиры и комнаты. Продам")) {
            return ObjectType.SALE_FLAT;
        } else if (title.contains("Квартиры и комнаты. Сдам")) {
            return ObjectType.LEASE_FLAT;
        } else if (title.contains("Дома. Дачи. Коттеджи") && title.contains("Дома, дачи, земельные участки. Продажа")) {
            return ObjectType.SALE_HOUSE;
        } else if (title.contains("Дома. Коттеджи. Дачи.") && title.contains("Дома, дачи, земельные участки. Аренда")) {
            return ObjectType.LEASE_HOUSE;
        } else if (title.contains("Земельные участки") && title.contains("Дома, дачи, земельные участки. Продажа")) {
            return ObjectType.SALE_LAND;
        }
        return null;
    }

    public ObjectType getObjectType(Document doc) {
        return getTypeFromTitle(getTitle(doc));
    }

    private String getTitle(Document doc) {
        Element title = getElementByTag("title", doc);
        return title.text().replaceAll("\n", " ").replaceAll("\\s+", " ");
    }

    private Element getElementByTag(String tagName, Document doc) {
        Elements elements = doc.getElementsByTag(tagName);
        if (elements.size() == 0) {
            throw new RuntimeException("The tag '" + tagName + "' can't be found");
        } else if (elements.size() > 1) {
            throw new RuntimeException("There are more than one tag '" + tagName + "'");
        }
        return elements.get(0);
    }
}
