package br.com.carpediem.controle.events
{
	import flash.events.Event;
	
	
	public class OKEvent extends Event
	{
		public static const OK:String = "@ok";
		
		public function OKEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
		
	}
}