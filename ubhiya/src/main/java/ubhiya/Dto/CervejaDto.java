package ubhiya.Dto;

public class CervejaDto {
	private Long idCerveja;
	private String nomeCerveja;
	private String fabricante;
	private String descricaoCerveja;
	private Integer ibu;
	private Double abv;
	
	public CervejaDto() {}
	
	public CervejaDto(Long idCerveja, String nomeCerveja, String fabricante, String descricaoCerveja, Integer ibu, Double abv) {
		this.setIdCerveja(idCerveja);
		this.setNomeCerveja(nomeCerveja);
		this.setFabricante(fabricante);
		this.setDescricaoCerveja(descricaoCerveja);
		this.setIbu(ibu);
		this.setAbv(abv);
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

	public String getDescricaoCerveja() {
		return descricaoCerveja;
	}

	public void setDescricaoCerveja(String descricaoCerveja) {
		this.descricaoCerveja = descricaoCerveja;
	}

	public void setIdCerveja(Long idCerveja) {
		this.idCerveja = idCerveja;
	}

	public void setNomeCerveja(String nomeCerveja) {
		this.nomeCerveja = nomeCerveja;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public void setIbu(Integer ibu) {
		this.ibu = ibu;
	}

	public void setAbv(Double abv) {
		this.abv = abv;
	}
}
