package br.com.carpediem.modelo
{
	import org.hamcrest.mxml.collection.InArray;

	[Bindable]
	[RemoteClass(alias="br.com.carpediem.modelo.Estado")]
	public class Estado
	{
		public var codigo:int;
		public var nome:String;
		public var uf:String;
	}
}