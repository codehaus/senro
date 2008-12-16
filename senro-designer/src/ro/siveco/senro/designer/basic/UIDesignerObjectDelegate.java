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
    public void setRow(String _row)
    {
        if(ObjectUtils.equals(rowExpression, _row)) {
            return;
        }
        rowExpression = _row == null ? "" : _row;
    }

    @Override
    public String getRow()
    {
        return rowExpression == null ? "" : rowExpression;
    }

    @Override
    public void setColumn(String _col)
    {
        if(ObjectUtils.equals(colExpression, _col)) {
            return;
        }
        colExpression = _col == null ? "" : _col;
    }

    @Override
    public String getColumn()
    {
        return colExpression == null ? "" : colExpression;
    }

}
