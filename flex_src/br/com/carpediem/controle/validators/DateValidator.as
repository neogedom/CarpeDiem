package br.com.carpediem.controle.validators
{
	import mx.controls.Alert;
	import mx.utils.StringUtil;
	
	
	
	
	
	public class DateValidator
	{
		public function DateValidator()
		{
		}
		
		public static function doValidation(s:Object):Boolean {
			var delimitador:String = "/";
			var d:Array = s.split(delimitador);
			var sDate:String = StringUtil.trim(s.toString());
			
			if (d.length!=3)
			{
				
				if (sDate.length == 0)
				{
					
					return true;
				}
				else {
					Alert.show(sDate.length.toString() + "OPASD");
					for (var i:Number = 0; i<d.length; i++)
					{
						d[i] = parseInt(d[i]);
					}
					
					d[1] = d[1]-1;	
					var dt:Date = new Date(d[2], d[1], d[0]);
					
					return ((dt.getFullYear()==d[2]) && (dt.getMonth()==d[1]) && (dt.getDate()==d[0]));
				}
			}
			
			return true;
			
		}
	}
}