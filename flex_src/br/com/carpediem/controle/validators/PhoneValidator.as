package br.com.carpediem.controle.validators
{
	import mx.controls.Alert;
	import mx.utils.StringUtil;

	public class PhoneValidator
	{
		public function PhoneValidator()
		{
		}
		
		public static function doValidation (value:Object):Boolean
		{
			var sPhone:String = StringUtil.trim(value.toString());
			var i:int = 0, j:int = 0;
			sPhone = sPhone.replace ("(","");
			sPhone = sPhone.replace (")","");
			sPhone = sPhone.replace(" ","");
			sPhone = sPhone.replace("-","");
			sPhone = sPhone.replace("     ","");
			
			
			if (sPhone.length != 10 && sPhone != "")
			{
				Alert.show("Telefone inválido","Atenção");
				return false;
			}
			
			else {
				return true;
			}	
			
		}
	}
}