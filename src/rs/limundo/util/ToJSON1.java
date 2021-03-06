package rs.limundo.util;






import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;



public class ToJSON1 {

	public JSONArray toJSONArray(ResultSet rs) throws Exception {
		
        JSONArray json = new JSONArray(); 
      

        try {

          java.sql.ResultSetMetaData rsmd = rs.getMetaData();

           
    
             
                  
             while( rs.next() ) {
            
                 int numColumns = rsmd.getColumnCount();
               
                 JSONObject obj = new JSONObject();
                 
               
              
                 for (int i=1; i<numColumns+1; i++) {

                     String column_name = rsmd.getColumnName(i);

                     if(rsmd.getColumnType(i)==java.sql.Types.ARRAY){
                    	 obj.put(column_name, rs.getArray(column_name));
                    	
                     }
                     else if(rsmd.getColumnType(i)==java.sql.Types.BIGINT){
                    	 obj.put(column_name, rs.getInt(column_name));
                     }
                     else if(rsmd.getColumnType(i)==java.sql.Types.BOOLEAN){
                    	 obj.put(column_name, rs.getBoolean(column_name));
                     }
                     else if(rsmd.getColumnType(i)==java.sql.Types.BLOB){
                    	 obj.put(column_name, rs.getBlob(column_name));
                     }
                     else if(rsmd.getColumnType(i)==java.sql.Types.DOUBLE){
                    	 obj.put(column_name, rs.getDouble(column_name)); 
                     }
                     else if(rsmd.getColumnType(i)==java.sql.Types.FLOAT){
                    	 obj.put(column_name, rs.getFloat(column_name));
                     }
                     else if(rsmd.getColumnType(i)==java.sql.Types.INTEGER){
                    	 obj.put(column_name, rs.getInt(column_name));
                     }
                     else if(rsmd.getColumnType(i)==java.sql.Types.NVARCHAR){
                    	 obj.put(column_name, rs.getNString(column_name));
                     }
                     else if(rsmd.getColumnType(i)==java.sql.Types.VARCHAR){
                    	 
                    	 //String sadrzaj=  new String(rs.getString(column_name).getBytes("ISO-8859-1"), "UTF-8");
                    	 //temp = rs.getString(column_name); //saving column data to temp variable
                    	// temp = ESAPI.encoder().canonicalize(temp); //decoding data to base state
                    	 //temp = ESAPI.encoder().encodeForHTML(temp); //encoding to be browser safe
                    	 //obj.put(column_name, temp); //putting data into JSON object
                    	 
                    	 obj.put(column_name, rs.getString(column_name));
                     }
              
                     else if(rsmd.getColumnType(i)==java.sql.Types.TINYINT){
                    	 obj.put(column_name, rs.getInt(column_name));
                     }
                     else if(rsmd.getColumnType(i)==java.sql.Types.SMALLINT){
                    	 obj.put(column_name, rs.getInt(column_name));
                     }
                     else if(rsmd.getColumnType(i)==java.sql.Types.DATE){
                    	 obj.put(column_name, rs.getDate(column_name));
                     }
                     else if(rsmd.getColumnType(i)==java.sql.Types.TIMESTAMP){
                    	 obj.put(column_name, rs.getTimestamp(column_name));
                     }
                     else if(rsmd.getColumnType(i)==java.sql.Types.NUMERIC){
                    	 obj.put(column_name, rs.getBigDecimal(column_name));
                      }
                     else {
                    	// String sadrzaj=  new String(rs.getString(column_name).getBytes("ISO-8859-1"), "UTF-8");
                    	 obj.put(column_name, rs.getString(column_name));
                     } 

                    }
                 json.put(obj);

             }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
	}
}