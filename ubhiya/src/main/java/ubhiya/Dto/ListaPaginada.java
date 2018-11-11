package ubhiya.Dto;

import java.util.List;

public class ListaPaginada<T> {
	private List<T> lista;
	private Long totalRegistros;
	
	public ListaPaginada(List<T> lista, Long totalRegistros) {
		this.lista=lista;
		this.totalRegistros=totalRegistros;
	}
	
	public List<T> getLista(){
		return this.lista;
	}
	
	public Long getTotalRegistros() {
		return this.totalRegistros;
	}
}
