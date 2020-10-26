package br.com.carpediem.reports.dataproviders
{
	import br.com.carpediem.modelo.Boletim;
	
	import mx.collections.ArrayCollection;

	public class DPTemplatePadrao
	{
		public var tabela:ArrayCollection;
		public var label:String;
		public var turmaCodigo:String;
		public var total:String;
		public var identificador:String;
		public var boletim:ArrayCollection = null;
		public var sala:String;
		
		public function DPTemplatePadrao()
		{
		}
	}
}