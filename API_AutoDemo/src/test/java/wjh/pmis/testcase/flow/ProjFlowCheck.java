package wjh.pmis.testcase.flow;

import org.testng.annotations.Test;

import wjh.pmis.data.RequestFlow;

import java.io.IOException;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeClass;

	/*
	 * 项目流水-整体流程
	 * 职能部门（安质部）
	 * 校验：
	 * 资金流水——成本
	 * 预算管控
	 */
public class ProjFlowCheck{
	//项目立项数据
	String proj_name_= "财务流水导出-借款";
	
	
	RequestFlow flow = new RequestFlow();
	
	//各模块之间流转的变量
	String proj_id_ = "";
	String proj_name = "";
	String proj_code = "";
	
	@BeforeClass
	public void setUp() {
	}
	//项目立项
	@Test
	public void flowProj() throws ClientProtocolException, IOException{
		
		Map<String,String> proj = flow.proj(proj_name_);
		proj_id_ = proj.get("proj_id_");
		proj_name = proj.get("proj_name");
		proj_code = proj.get("proj_code");
	}	
	//费用报销
//	@Test(priority=1)
	public void flowFybx() throws ClientProtocolException, IOException{
		flow.fybx("100", "住宿费", proj_id_, proj_code, proj_name);
		flow.fybx("200", "宿舍杂费", proj_id_, proj_code, proj_name);
		flow.fybx("300", "宿舍房租", proj_id_, proj_code, proj_name);
		flow.fybx("400", "市内交通费", proj_id_, proj_code, proj_name);
		flow.fybx("500", "培训相关费用", proj_id_, proj_code, proj_name);
		flow.fybx("600", "会议费", proj_id_, proj_code, proj_name);
		flow.fybx("700", "评审费", proj_id_, proj_code, proj_name);
		flow.fybx("800", "项目打印", proj_id_, proj_code, proj_name);
		flow.fybx("900", "油费", proj_id_, proj_code, proj_name);
		flow.fybx("1000", "租车费", proj_id_, proj_code, proj_name);
		flow.fybx("1100", "办公房租", proj_id_, proj_code, proj_name);
		flow.fybx("1200", "新闻", proj_id_, proj_code, proj_name);
		flow.fybx("1300", "图书", proj_id_, proj_code, proj_name);
		flow.fybx("1400", "快递", proj_id_, proj_code, proj_name);
		flow.fybx("1500", "耗材、办公用品", proj_id_, proj_code, proj_name);
		flow.fybx("1600", "加班餐费", proj_id_, proj_code, proj_name);
		flow.fybx("1700", "通讯费", proj_id_, proj_code, proj_name);
		flow.fybx("1800", "办公打印", proj_id_, proj_code, proj_name);
		flow.fybx("1900", "办公维修", proj_id_, proj_code, proj_name);
		flow.fybx("2000", "办公其他", proj_id_, proj_code, proj_name);
		flow.fybx("2100", "招待费", proj_id_, proj_code, proj_name);
		flow.fybx("2200", "标书费", proj_id_, proj_code, proj_name);
		flow.fybx("2300", "中标服务费", proj_id_, proj_code, proj_name);
		flow.fybx("2400", "实习生工资", proj_id_, proj_code, proj_name);
		flow.fybx("2500", "外业补助", proj_id_, proj_code, proj_name);
		flow.fybx("2600", "项目奖", proj_id_, proj_code, proj_name);
		flow.fybx("260", "其他", proj_id_, proj_code, proj_name);
	}
	
	//差旅报销
//	@Test(priority=1)
	public void flowClbx() throws ClientProtocolException, IOException {
		flow.clbx(250, proj_id_, proj_code, proj_name);
	}
	
	
		
	//借款
	@Test(priority=2)
	public void flowLoan() throws ClientProtocolException, IOException{
		flow.loan("100", "差旅费", proj_name, proj_id_);
		flow.loan("200", "采购费", proj_name, proj_id_);
		flow.loan("300", "评审费", proj_name, proj_id_);
		flow.loan("400", "办公费", proj_name, proj_id_);
		flow.loan("500", "招待费", proj_name, proj_id_);
		flow.loan("550", "标书费", proj_name, proj_id_);
		flow.loan("600", "中标服务费", proj_name, proj_id_);
		flow.loan("700", "投标保证金", proj_name, proj_id_);
		flow.loan("800", "实习生工资", proj_name, proj_id_);
	}
	
	
}
