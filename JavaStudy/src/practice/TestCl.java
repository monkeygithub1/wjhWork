package practice;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
public class TestCl {

	public static void main(String[] args) throws JSONException {
		// TODO Auto-generated method stub


//		String cxId = vars.get("cxId");
//		String clUserIds = vars.get("clUserIds");
//		String clUserNames = vars.get("clUserNames");
//		String projId = vars.get("projId");
//		String projCode = vars.get("projCode");
//		String projName = vars.get("projName");
		String cxId = "111";
		String clUserIds = "222";
		String clUserNames = "333";
		String projId = "444";
		String projCode = "555";
		String projName = "666";

		double money_buzhu = 10.12;
		double money_jt =  15.23;
		double money_sum = money_buzhu + money_jt;
		String money_bz = String.valueOf(money_buzhu);
		String money_traffic = String.valueOf(money_jt);
		String money = String.valueOf(money_sum);
//		String money_bz = "10.11";
//		String money_traffic = "5.23";
//		String money = "15.34";

		JSONObject clbxDto = new JSONObject();
		clbxDto.put("projId", projId);
		clbxDto.put("projCode", projCode);
		clbxDto.put("projName", projName);
		clbxDto.put("totalAmount", money);//总报销金额
		clbxDto.put("bxrIds", "3b8f52b4b8f54bd597557361efcb8736");
		clbxDto.put("bxrNames", "武军豪");
		clbxDto.put("pmo", "");
		clbxDto.put("typeFlag", "");
		clbxDto.put("subsidyAmount", money_bz);//补助金额
		clbxDto.put("xjAmount", 0);
		clbxDto.put("xjTip", "");
		clbxDto.put("czJson", "");

		JSONArray details = new JSONArray();
		JSONObject details_traffic = new JSONObject();
		details_traffic.put("bxType", "traffic");
		details_traffic.put("amount", money_traffic);//明细报销金额
		details_traffic.put("travelToolName", "城际一等座");
		details_traffic.put("travelFromAddr", "北京");
		details_traffic.put("travelToAddr", "天津");
		details_traffic.put("stayCity", "");
		details_traffic.put("st", "2018-08-16 00:00:00");
		details_traffic.put("et", "2018-08-23 00:00:00");
		details_traffic.put("tip", "顺顺大猪蹄子");
		details_traffic.put("clUserIds", clUserIds);
		details_traffic.put("clUserNames", clUserNames);
		details.put(0, details_traffic);

		JSONArray recieve = new JSONArray();
		JSONObject recieve_user = new JSONObject();
		recieve_user.put("userId", "3b8f52b4b8f54bd597557361efcb8736");
		recieve_user.put("userName", "武军豪");
		recieve_user.put("paymentAmount", money);
		recieve_user.put("paymentMethod", "银行卡");
		recieve_user.put("tip", "顺顺大猪蹄子");
		recieve.put(0, recieve_user);

		JSONArray chuxings = new JSONArray();
		JSONObject cx = new JSONObject();
		cx.put("id", cxId);
		//cx.put("cxId", null);
		cx.put("didian", "");
		cx.put("proposer", "");
		cx.put("proposerName", "");
		cx.put("st", "2018-08-16 00:00:00");
		cx.put("et", "2018-08-23 00:00:00");
		cx.put("txr", "");
		cx.put("beizhu", "");
		//cx.put("createTime", null);
		//cx.put("status", null);
		cx.put("mudi", "顺顺ZZ");
		chuxings.put(0, cx);

		JSONObject clbxJson  = new JSONObject();
		clbxJson.put("saveFilePath","");
		clbxJson.put("clbxDto", clbxDto);
		clbxJson.put("details", details);
		clbxJson.put("recieve", recieve);
		clbxJson.put("chuxings", chuxings);
		String json = clbxJson.toString();
		System.out.println(json);
		//vars.put("bxJson",json);


	}

}
