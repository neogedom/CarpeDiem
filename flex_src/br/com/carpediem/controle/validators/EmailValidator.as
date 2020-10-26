package br.com.carpediem.controle.validators
{
	import mx.controls.Alert;
	import mx.utils.StringUtil;
	import mx.validators.ValidationResult;
	import mx.validators.Validator;
	
	public class EmailValidator
	{
	
		
		public function EmailValidator()
		{
		}
	
		public static function doValidation (value:Object):Boolean
		{
			var sEmail:String = StringUtil.trim(value.toString());
			var i:int = 0, j:int = 0;
			
			if (sEmail == "")
			{
				return true;
			}
			for (i = 0; i <= sEmail.length; i++)
			{
				if (sEmail.charAt(i) == "@")
				{
					for (j = 0; j <= sEmail.length; j++)
					{
						if (sEmail.charAt(j) == ".")
						{
							return true;
						}
					}
				}
			
			}
			Alert.show("Email inválido!", "Atenção");
			return false;
			
		}
	}
}