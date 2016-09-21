package com.tabeeby.doctor.model;

/**
 * Created by Lenovo R61 on 9/20/2016.
 */
public class QuastionAndAnswreModel {
    private String message;

    private String status;

    private DataModel data;

    private String code;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public DataModel getData ()
    {
        return data;
    }

    public void setData (DataModel data)
    {
        this.data = data;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

}
