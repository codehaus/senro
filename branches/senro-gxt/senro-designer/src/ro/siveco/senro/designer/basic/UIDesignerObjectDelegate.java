package ro.siveco.senro.designer.basic;

import org.apache.commons.lang.ObjectUtils;

import java.io.Serializable;

public class UIDesignerObjectDelegate extends SenroDesignerObjectDelegate implements UIDesignerObject, Serializable
{
    private static final long serialVersionUID = 1L;
    private String rowExpression = "";
    private String colExpression = "";

    public UIDesignerObjectDelegate(SenroDesignerObject senroObject)
    {
        super(senroObject);
    }

    @Override
    public void setRowExpr(String row_expr)
    {
        if(ObjectUtils.equals(rowExpression, row_expr)) {
            return;
        }
        rowExpression = row_expr == null ? "" : row_expr;
    }

    @Override
    public String getRowExpr()
    {
        return rowExpression == null ? "" : rowExpression;
    }

    @Override
    public void setColumnExpr(String col_expr)
    {
        if(ObjectUtils.equals(colExpression, col_expr)) {
            return;
        }
        colExpression = col_expr == null ? "" : col_expr;
    }

    @Override
    public String getColumnExpr()
    {
        return colExpression == null ? "" : colExpression;
    }

}
