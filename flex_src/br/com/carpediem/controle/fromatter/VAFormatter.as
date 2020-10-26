package br.com.carpediem.controle.fromatter
{
	import mx.controls.Alert;
	
	import spark.formatters.NumberFormatter;
	
	public class VAFormatter 
	{
		[Bindable]
		private var nf:NumberFormatter = new NumberFormatter();
		
		public function doFormat(value:Object):Object
		{
			var n:String;
			if (value.toString() == "-1")
			{
				n = "não lançada";
				
			}
			
			else 
			{
				var number:Number = value as Number;
				number.toPrecision(1);
				n = nf.format(number);
							
			}
			
			return n;
		}
	}
}