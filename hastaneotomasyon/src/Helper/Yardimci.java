package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;


public class Yardimci {
	
	public static void changeButtonTextToTurkish() {
		UIManager.put("OptionPane.cancelButtonText", "�ptal");
		UIManager.put("OptionPane.noButtonText", "Hay�r");		
		UIManager.put("OptionPane.okButtonText", "Tamam");		
		UIManager.put("OptionPane.yesButtonText", "Evet");		
		
	}
	
	
	public static void showMsg(String str) {
	String msg;
	changeButtonTextToTurkish();
	switch (str) {
	case "!dolu": {
		msg="L�tfen t�m alanlar� doldurunuz";
		break;
	}
	case "success": {
		msg="��lem Ba�ar�l� !";
		break;
	}
	case "error":{
		msg="Bir hata ger�ekle�ti!";
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
			msg="Bu i�lemi ger�ekle�tirmek istiyor musunuz?";
			break;
		}
		default:
			msg=str;
			break;	
		}
		int res= JOptionPane.showConfirmDialog(null, msg,"D�KKAT!",JOptionPane.YES_NO_OPTION);
		if (res==0)	 {
			return true;
			
		}
		else {
			return false;
		}
	}

}
