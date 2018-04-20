//package entities;
//
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonDeserializationContext;
//import com.google.gson.JsonDeserializer;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParseException;
//
//public class CondicionDeserializador implements JsonDeserializer<Condicion> {
//
//	private Formula deserializarFormula(JsonObject Jo){
//		JsonObject jo = Jo.get("formulaObjeto").getAsJsonObject();
//		Formula fo = new Formula();
//		ArrayList<String> lista = new ArrayList<String>();
//		JsonArray listajs=jo.get("nombreCuentas").getAsJsonArray();
//	    for (int i = 0; i < listajs.size(); i++) {
//	        JsonElement jsonString = listajs.get(i);
//	        lista.add(jsonString.getAsString());
//	      }
//	    fo.setNombreCuentas(lista);
//		return fo;
//	}
//	
//	private Indicador deserializarIndicador(JsonObject Jo){
//		JsonObject jo = Jo.get("Indicador").getAsJsonObject();
//		Indicador i=new Indicador();
//		i.setNombreIndicador(jo.get("nombreIndicador").getAsString());
//		i.setFormula(jo.get("formula").getAsString());
//		i.setFormulaObjeto(deserializarFormula(jo));
////		i.setUsuario(jo.get("idUsuario").getAsInt());
//		i.setIdIndicador(jo.get("idIndicador").getAsInt());
//		return i;
//	}
//	@Override
//	public Condicion deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//		JsonObject jsonObject = json.getAsJsonObject();
//		System.out.println(jsonObject.get("Operacion").getAsString());
//		System.out.println(jsonObject.get("Indicador").getAsJsonObject().get("nombreIndicador").getAsString());
//		if(jsonObject.get("Tipo").getAsString().equals("TAXATIVA")){
//			return new CondicionTaxativa(
//					jsonObject.get("Operacion").getAsString(),
//					this.deserializarIndicador(jsonObject),
//					jsonObject.get("Entero").getAsInt()
//					);
//			}else{
//			return new CondicionDeOrden(
//					jsonObject.get("Operacion").getAsString(),
//					this.deserializarIndicador(jsonObject),
//					jsonObject.get("Entero").getAsInt()
//					);
//			}
//	}
//
//}
