package ro.siveco.senro.designer.basic;

public interface UIDesignerObject extends SenroDesignerObject
{
    public void setRowExpr(String row);
    public String getRowExpr();

    public void setColumnExpr(String col);
    public String getColumnExpr();

}
