package ubhiya.Dto;

public class CervejaDto {
	private Long idCerveja;
	private String nomeCerveja;
	private String fabricante;
	private Integer ibu;
	private Double abv;
	
	public CervejaDto(Long idCerveja, String nomeCerveja, String fabricante, Integer ibu, Double abv) {
		this.idCerveja = idCerveja;
		this.nomeCerveja = nomeCerveja;
		this.fabricante = fabricante;
		this.ibu = ibu;
		this.abv = abv;
	}
	
	public Long getIdCerveja() {
		return this.idCerveja;
	}
	
	public String getNomeCerveja() {
		return this.nomeCerveja;
	}
	
	public String getFabricante() {
		return this.fabricante;
	}
	
	public Integer getIbu() {
		return this.ibu;
	}
	
	public Double getAbv() {
		return this.abv;
	}
}
