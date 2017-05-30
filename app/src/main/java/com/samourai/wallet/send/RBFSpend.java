package com.samourai.wallet.send;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RBFSpend    {

    private String strHash = null;
    private List<String> changeAddrs = null;
    private String strSerializedTx = null;

    public RBFSpend(String strHash, List<String> changeAddrs, String strSerializedTx)  {
        this.strHash = strHash;
        this.changeAddrs = changeAddrs;
        this.strSerializedTx = strSerializedTx;
    }

    public RBFSpend()  {
        changeAddrs = new ArrayList<String>();
    }

    public String getHash() {
        return strHash;
    }

    public void setHash(String strHash) {
        this.strHash = strHash;
    }

    public List<String> getChangeAddrs() {
        return changeAddrs;
    }

    public void setChangeAddrs(List<String> changeAddrs) {
        this.changeAddrs = changeAddrs;
    }

    public void addChangeAddr(String addr)   {
        changeAddrs.add(addr);
    }

    public boolean containsChangeAddr(String addr)   {
        return changeAddrs.contains(addr);
    }

    public String getSerializedTx() {
        return strSerializedTx;
    }

    public void setSerializedTx(String strSerializedTx) {
        this.strSerializedTx = strSerializedTx;
    }

    public JSONObject toJSON() {

        JSONObject jsonPayload = new JSONObject();
        JSONArray array = null;
        try {
            array = new JSONArray();

            for(String addr : changeAddrs)   {
                array.put(addr);
            }

            jsonPayload.put("change_addresses", array);

            if(strHash != null)    {
                jsonPayload.put("hash", strHash);
            }

            if(strSerializedTx != null)    {
                jsonPayload.put("tx", strSerializedTx);
            }

        }
        catch(JSONException je) {
            ;
        }

        return jsonPayload;
    }

    public void fromJSON(JSONObject jsonPayload) {

        try {

            if(jsonPayload.has("change_addresses"))    {

                JSONArray array = jsonPayload.getJSONArray("change_addresses");

                for(int i = 0; i < array.length(); i++)   {
                    changeAddrs.add((String)array.get(i));
                }

            }

            if(jsonPayload.has("hash"))    {
                strHash = jsonPayload.getString("hash");
            }

            if(jsonPayload.has("tx"))    {
                strSerializedTx = jsonPayload.getString("tx");
            }

        }
        catch(JSONException ex) {
            throw new RuntimeException(ex);
        }

    }

}
