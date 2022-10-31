package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;


public class Yardimci {
	
	public static void changeButtonTextToTurkish() {
		UIManager.put("OptionPane.cancelButtonText", "Ýptal");
		UIManager.put("OptionPane.noButtonText", "Hayýr");		
		UIManager.put("OptionPane.okButtonText", "Tamam");		
		UIManager.put("OptionPane.yesButtonText", "Evet");		
		
	}
	
	
	public static void showMsg(String str) {
	String msg;
	changeButtonTextToTurkish();
	switch (str) {
	case "!dolu": {
		msg="Lütfen tüm alanlarý doldurunuz";
		break;
	}
	case "success": {
		msg="Ýþlem Baþarýlý !";
		break;
	}
	case "error":{
		msg="Bir hata gerçekleþti!";
		break;
	}
	default:
		msg=str;
		
	}
	JOptionPane.showMessageDialog(null, msg,"Mesaj",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static boolean showConfirm(String str) {
		String msg;
		changeButtonTextToTurkish();
		switch (str) {
		case "sure": {
			msg="Bu iþlemi gerçekleþtirmek istiyor musunuz?";
			break;
		}
		default:
			msg=str;
			break;	
		}
		int res= JOptionPane.showConfirmDialog(null, msg,"DÝKKAT!",JOptionPane.YES_NO_OPTION);
		if (res==0)	 {
			return true;
			
		}
		else {
			return false;
		}
	}

}
