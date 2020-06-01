package model.bl;

import model.hibernate.HibernateUtil;
import model.to.EPGModelTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;

public class EPGModelDAO {
    public Session session;
    public Transaction transaction;

    public String getEPG(String chName)
    {
        try
        {
            session= HibernateUtil.getSession();

            Iterator<EPGModelTO>iterator=session.createQuery("from EPGModelTO where CName= :cName")
                    .setParameter("cName",chName)
                    .iterate();

            JSONArray jsonArray=new JSONArray();
            while (iterator.hasNext())
            {
                JSONObject jsonObject=new JSONObject();
                EPGModelTO epgModelTO=iterator.next();
                jsonObject.put("ID",epgModelTO.getID());
                jsonObject.put("CName",epgModelTO.getCName());
                jsonObject.put("Title",epgModelTO.getTitle());
                jsonObject.put("Description",epgModelTO.getDescription());
                jsonArray.add(jsonObject);

            }
            session.close();
            return jsonArray.toJSONString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
