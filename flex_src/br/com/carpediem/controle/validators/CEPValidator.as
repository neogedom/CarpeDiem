package br.com.carpediem.controle.validators
{
	import mx.controls.Alert;
	import mx.utils.StringUtil;

	public class CEPValidator
	{
		public function CEPValidator()
		{
		}
		
		public static function doValidation (value:Object):Boolean
		{
			var sCEP:String = StringUtil.trim(value.toString());
			var i:int = 0, j:int = 0;
			sCEP = sCEP.replace("-","");
			
			if (sCEP.length != 8 && sCEP != "")
			{
				Alert.show("CEP inválido","Atenção");
				return false;
			}
				
			else {
				return true;
			}	
			
		}
	}
	
}
