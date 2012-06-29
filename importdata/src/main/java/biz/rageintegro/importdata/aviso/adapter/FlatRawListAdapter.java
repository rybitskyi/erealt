package biz.rageintegro.importdata.aviso.adapter;

import biz.rageintegro.importdata.aviso.dto.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class FlatRawListAdapter extends AbstractRawListAdapter<FlatRawItemDTO> {
    private Integer roomCount;

    @Override
    protected void fillRawItemDTO(FlatRawItemDTO item, Element div, RawListAdapterContext context) {
        super.fillRawItemDTO(item, div, context);
        item.setRoomCount(roomCount);
    }

    private Integer getRoomCount(Document doc) {
        Elements inputs = doc.select("input[class=in-rooms]");
        for (Element input : inputs) {
            String attrValue = input.attr("value");
            if (attrValue != null && attrValue.length() > 0) {
                String name = input.attr("name");
                return Integer.parseInt(name.replaceAll("rooms", ""));
            }
        }
        return null;
    }

    protected void initPageParams(RawListAdapterContext context) {
        super.initPageParams(context);
        roomCount = getRoomCount(context.getDoc());
    }
}
