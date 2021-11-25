package Main.Tools;

import java.util.ArrayList;
import Main.SperryMarine.Object.Objects;

public class ObjectGenerator {
    public ObjectGenerator() {
    }

    public String generateObject(Objects o) {
        StringBuffer s = new StringBuffer();
        s.append("<Objects>").append("\n");
        s.append(generateObjectId(o)).append("\n");
        s.append(generateType(o)).append("\n");
        s.append(generateLatMin(o)).append("\n");
        s.append(generateLonMin(o)).append("\n");
        s.append(generateLatMax(o)).append("\n");
        s.append(generateLonMax(o)).append("\n");
        s.append(generateLayerId(o)).append("\n");
        s.append(generateVert(o)).append("\n");
        s.append(generateRotation(o)).append("\n");
        s.append(generateCreateTime(o)).append("\n");
        s.append(generateCreateShip(o)).append("\n");
        s.append(generateModifyTime(o)).append("\n");
        s.append(generateModifyUser(o)).append("\n");
        s.append(generateName(o)).append("\n");
        s.append(generateLabel(o)).append("\n");
        s.append(generateNote(o)).append("\n");
        s.append(generateChartEdition(o)).append("\n");
        s.append(generateArchived(o)).append("\n");
        s.append(generateTimeLabel(o)).append("\n");
        s.append(generateDependentType(o)).append("\n");
        s.append(generateDateDependentStart(o)).append("\n");
        s.append(generateDateDependentEnd(o)).append("\n");
        s.append(generateHasLight(o)).append("\n");
        s.append(generateLineSegmentType(o)).append("\n");
        s.append(generateGeometryAttributes(o)).append("\n");
        s.append(generateCustomLineColor(o)).append("\n");
        s.append(generateLineStyle(o)).append("\n");
        s.append( generateLineWeight(o)).append("\n");
        s.append(generateArrowType(o)).append("\n");
        s.append(generateShowArrowAtStart(o)).append("\n");
        s.append(generateShowArrowsAtEng(o)).append("\n");
        s.append(generateFillColor(o)).append("\n");
        s.append( generateFillPattern(o)).append("\n");
        s.append( generateOpacity(o)).append("\n");
        s.append(generateAltitudes(o)).append("\n");
        s.append("</Objects>").append("\n");
        return s.toString();
    }

    private String generateObjectId(Objects o) {
        StringBuffer s = new StringBuffer();
        StringBuffer objID = new StringBuffer(o.getObjectID());
        objID.insert(8,"-").insert(13,"-").insert(18,"-").insert(23,"-");
        return s.append("<ObjectId>").append(objID).append("</ObjectId>").toString();
    }
    private String generateType(Objects o) {
        StringBuffer s = new StringBuffer();
        return s.append("<Type>").append(o.getType()).append("</Type>").toString();
    }
    private String generateLatMin(Objects o) {
        StringBuffer s = new StringBuffer();
        return  s.append("<LatMin>").append(o.getLatMin()).append("</LatMin>").toString();
    }
    private String generateLatMax(Objects o) {
        StringBuffer s = new StringBuffer();
        return  s.append("<LatMax>").append(o.getLatMax()).append("</LatMax>").toString();
    }
    private String generateLonMin(Objects o) {
        StringBuffer s = new StringBuffer();
        return  s.append("<LonMin>").append(o.getLonMin()).append("</LonMin>").toString();
    }
    private String generateLonMax(Objects o) {
        StringBuffer s = new StringBuffer();
        return  s.append("<LonMax>").append(o.getLonMax()).append("</LonMax>").toString();
    }
    private String generateLayerId(Objects o) {
        StringBuffer s = new StringBuffer();
        return s.append("<LayerId>").append(o.getLayerID()).append("</LayerId>").toString();
    }

    private String generateVert(Objects o) {
        StringBuffer s = new StringBuffer();
        s.append("<Vertices>");
        ArrayList<Double> vert = o.getVertices();
        for(Double d : vert) {
            s.append(d).append(",");
        }
        s.deleteCharAt(s.length()-1);
        s.append("</Vertices>");

        return s.toString();
    }

    private String generateRotation(Objects o) {
        StringBuffer s = new StringBuffer();
        return s.append("<Rotation>").append(o.getRotation()).append("</Rotation>").toString();
    }

    private String generateCreateTime(Objects o) {
        StringBuffer s = new StringBuffer();
        return  s.append("<CreateTime>").append(o.getCreateTime()).append("</CreateTime>").toString();
    }
    private String generateCreateShip(Objects o) {
        StringBuffer s = new StringBuffer();
        return s.append("<CreateShip>").append(o.getCreateShip()).append("</CreateShip>").toString();
    }
    private String generateModifyTime(Objects o) {
        StringBuffer s = new StringBuffer();
        return s.append("<ModifyTime>").append(o.getModifyTime()).append("</ModifyTime>").toString();
    }
    private String generateModifyUser(Objects o){
        StringBuffer s = new StringBuffer();
        return s.append("<ModifyUser>").append(o.getModifyUser()).append("</ModifyUser>").toString();
    }

    private String generateName(Objects o) {
        StringBuffer s = new StringBuffer();
        return s.append("<Name>").append(o.getName()).append("</Name>").toString();
    }
    private String generateLabel(Objects o) {
        StringBuffer s = new StringBuffer();
        return s.append("<Label>").append(o.getLabel()).append("</Label>").toString();
    }
    private String generateNote(Objects o) {
        StringBuffer s = new StringBuffer();
        return s.append("<Note>").append(o.getNote()).append("</Note>").toString();
    }
    private String generateChartEdition(Objects o) {
        return "<ChartEdition>-1</ChartEdition>";
    }

    private String generateArchived(Objects o) {
        StringBuffer s = new StringBuffer();
        return s.append("<Archived>").append(o.isArchived()).append("</Archived>").toString();
    }
    private String generateTimeLabel(Objects o) {
        StringBuffer s = new StringBuffer();
        return s.append("<TimeLabel />").toString();
    }
    private String generateDependentType(Objects o) {
        return "<DateDependentType>" + String.valueOf(o.getDateDependentType()) + "</DateDependentType>";
    }
    private String generateDateDependentStart(Objects o) {
        return "<DateDependentStart>" + o.getDateDependentStart() + "</DateDependentStart>";
    }
    private String generateDateDependentEnd(Objects o) {
        return "<DateDependentEnd>" + o.getGetDateDependentEnd() + "</DateDependentEnd>";
    }
    private String generateHasLight(Objects o) {
        String s = o.isHasLight() ? "true" : "false";
        return "<HasLight>" + s + "</HasLight>";
    }
    private String generateLineSegmentType(Objects o) {
        return  "<Line" + "SegmentType>" + o.getLineSegmentType() + "</LineSegmentType>";
    }
    private String generateGeometryAttributes(Objects o) {
        return "<GeometryAttributes>" + o.getGeometryAttributes() + "</GeometryAttributes>";
    }
    private String generateCustomLineColor(Objects o) {
        return  "<CustomLineColor>" + o.getCustomLineColor() + "</CustomLineColor>";
    }
    private String generateLineStyle(Objects o) {
        return "<LineStyle>" + o.getLineStyle() + "</LineStyle>";
    }
    private String generateLineWeight(Objects o) {
        return "<LineWeight>" + o.getLineWeight() + "</LineWeight>";
    }
    private String generateArrowType(Objects o) { return "<LineArrowType>" + o.getLineArrowType() + "</LineArrowType>";}
    private String generateShowArrowAtStart(Objects o) {
        String s = o.isShowArrowAtStart() ? "true" : "false";
        return "<ShowArrowsAtStart>" + s + "</ShowArrowsAtStart>";
    }
    private String generateShowArrowsAtEng(Objects o) {
        String s = o.isShowArrowAtEnd() ? "true" : "false";
        return "<ShowArrowsAtEnd>" + s + "</ShowArrowsAtEnd>";
    }
    private String generateFillColor(Objects o) {
        return "<FillColor>" + o.getFillColor() + "</FillColor>";
    }
    private String generateFillPattern(Objects o) {
        return  "<FillPattern>" + o.getFillPattern() + "</FillPattern>";
    }
    private String generateOpacity(Objects o) {
        return "<Opacity>" + o.getOpacity() + "</Opacity>";
    }
    private String generateAltitudes(Objects o) {
        return "<Altitudes>" + o.generateAltidutes() + "</Altitudes>";
    }
}

