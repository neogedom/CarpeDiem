package br.com.carpediem.controle.validators
{
	import mx.controls.Alert;
	import mx.resources.ResourceBundle;
	import mx.utils.StringUtil;
	import mx.validators.ValidationResult;
	import mx.validators.Validator;     
	
	public class CNPJValidator extends Validator {
		
		//--------------------------------------------------------------------------
		//
		//  Class resources
		//
		//--------------------------------------------------------------------------
		
		//[ResourceBundle("validators")]
		
		/**
		 *  A ResourceBundle object containing all symbols
		 *  from <code>cnpjcpfformatters.properties</code>.
		 */
		
		/*private static var packageResources:ResourceBundle;*/
		
		/**
		 *  @private    
		 */ 
		/*private static var resourceInvalidNumber:Boolean;*/
		
		/*private static var resourceEmptyNumber:Boolean;*/             
		
		//--------------------------------------------------------------------------
		//
		//  Class initialization
		//
		//--------------------------------------------------------------------------
		
		/*loadResources();*/
	  
		
		
		//--------------------------------------------------------------------------
		//
		//  Class methods
		//
		//--------------------------------------------------------------------------
		
		/**
		 *  @private    
		 *  Loads resources for this class.
		 */
		/*private static function loadResources():void
		{
		// packageResources was loaded by Formatter superclass.
		
		// Class properties             
		resourceInvalidNumber = packageResources.getString("resourceInvalidNumber");
		
		resourceEmptyNumber = packageResources.getString("resourceEmptyNumber");
		}*/
		
		//--------------------------------------------------------------------------
		//
		//  Constructor
		//
		//--------------------------------------------------------------------------
		
		public function CNPJValidator() {
		}               
		
	 	public static function doValidation (value:Object):Boolean{
			
			var sCnpj:String = StringUtil.trim(value.toString());
			sCnpj = sCnpj.replace (".","");
			sCnpj = sCnpj.replace (".","");
			sCnpj = sCnpj.replace("-","");
			sCnpj = sCnpj.replace("/","");
			sCnpj = sCnpj.replace("         ", "");
			if (sCnpj == "00000000000000" || sCnpj == "11111111111111" || sCnpj == "22222222222222" ||
				sCnpj == "33333333333333" || sCnpj == "44444444444444" || sCnpj == "55555555555555" ||
				sCnpj == "66666666666666" || sCnpj == "77777777777777" || sCnpj == "88888888888888" ||
				sCnpj == "99999999999999") 
			{
				
				Alert.show("Número do CNPJ inválido1", "Atenção");
				return false;
			} 
			else if ((sCnpj.length == 14) && (!isNaN(Number(sCnpj))) || sCnpj.length == 1)
			{       
				if (fnValidaCNPJ(sCnpj))
				{
					return true;
				}
				else {
					Alert.show("Número do CNPJ inválido2", "Atenção");
					return false;
				}
			}else 
			{
				Alert.show("Número do CNPJ inválido.3", "Atenção");
				return false;
			}                 
		}
		
		public static function fnValidaCNPJ(CNPJ:String):Boolean{
			var a:Array = [];
			var b:int = 0, i:int, x:int, y:int;
			var c:Array = [6,5,4,3,2,9,8,7,6,5,4,3,2];
			
			if (CNPJ.length == 0)
			{
				return true;
			}
			
			for (i=0; i<12; i++){
				a[i] = CNPJ.charAt(i);
				b += a[i] * c[i+1];
			}
			if ((x = b % 11) < 2) {
				a[12] = 0 ;
			} else { 
				a[12] = 11-x ;
			}
			b = 0;
			for (y=0; y<13; y++) {
				b += (a[y] * c[y]);
			}
			if ((x = b % 11) < 2) {
				a[13] = 0; 
			} else { 
				a[13] = 11-x;
			}
			if ((CNPJ.charAt(12) != a[12]) || (CNPJ.charAt(13) != a[13])){
				return false;
			}
			return true;
		}
	}

}