package br.com.carpediem.controle.validators
{
	import mx.controls.Alert;
	import mx.utils.StringUtil;
	import mx.validators.ValidationResult;
	import mx.validators.Validator;
	
	public class CPFValidator
	{
			
		public function CPFValidator() 
		{
		}               
		
		public static function doValidation (value:Object):Boolean
		{
			var sCpf:String = StringUtil.trim(value.toString());
			sCpf = sCpf.replace (".","");
			sCpf = sCpf.replace (".","");
			sCpf = sCpf.replace("-","");
			sCpf = sCpf.replace("     ", "");
		
			if (sCpf == "00000000000" || sCpf == "11111111111" || sCpf == "22222222222" ||
				sCpf == "33333333333" || sCpf == "44444444444" || sCpf == "55555555555" ||
				sCpf == "66666666666" || sCpf == "77777777777" || sCpf == "88888888888" ||
				sCpf == "99999999999")
			{
				Alert.show("Número do CPF inválido", "Atenção");
				return false;
			} 
			else if ((sCpf.length == 11) && (!isNaN(Number(sCpf))) || sCpf == " ")
			{       
				if (fnValidaCPF(sCpf))
				{
					return true;
				}
				else {
					Alert.show("Número do CPF inválido", "Atenção");
					return false;
				}
			} else 
			{
				Alert.show("Número do CPF inválido.", "Atenção");
				return false;
			}
			
		}
		
		public static function fnValidaCPF(NUMERO:String):Boolean{
			
			var x:int, soma:int, dig1:int, dig2:int, len:int, i:int = 0;
			var texto:String, numcpf1:String, y:String = "";
			len = NUMERO.length; x = len -1;
			
			if (NUMERO == " ")
			{
				return true;
			}
			
			for (i=0; i <= len - 3; i++) {
				y = NUMERO.substring(i,i+1);
				soma = soma + ( int(y) * x);
				x = x - 1;
				texto = texto + y;
			}
			
			// % retorna o resto da divisão por 11
			dig1 = 11 - (soma % 11);
			
			if (dig1 == 10) dig1=0 ;
			if (dig1 == 11) dig1=0 ;
			
			numcpf1 = NUMERO.substring(0,len - 2) + dig1 ;
			x = 11; soma=0;
			
			for (i=0; i <= len - 2; i++) {
				soma = soma + (int(numcpf1.substring(i,i+1)) * int(x));
				x = x - 1;
				
			}
			dig2= 11 - (soma % 11);
			
			if (dig2 == 10) dig2=0;
			if (dig2 == 11) dig2=0;
			
			if ((dig1 + "" + dig2) == NUMERO.substring(len,len-2)) {
				return true;
			}
			
			return false;
			
		}       
	}

	}
