package models;

public class Product {
	private int id; 
	private String productName;
	private String categoria;
	private String fabricacao;
	private String validade;
	
	public Product() {}
	
	public Product(String productName, String categoria, String fabricacao, String validade) {
		super();
		this.productName = productName;
		this.categoria = categoria;
		this.fabricacao = fabricacao;
		this.validade = validade;
	}
        
	public Product(int id, String productName, String categoria, String fabricacao, String validade) {
		this(productName, categoria, fabricacao, validade);
		setId(id);
	}
	
	public Product(String productName, String categoria, String fabricacao) {
		super();
		this.productName = productName;
		this.categoria = categoria;
		this.fabricacao = fabricacao;
	}
	
	public Product(String productName, String categoria) {
		super();
		this.productName = productName;
		this.categoria = categoria;
	}

	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getFabricacao() {
		return fabricacao;
	}
	
	public void setFabricacao(String fabricacao) {
		this.fabricacao = fabricacao;
	}
	
	public String getValidade() {
		return validade;
	}
	
	public void setValidade(String validade) {
		this.validade = validade;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		String s = id+" "+ productName+ " "+categoria;
		if(fabricacao!=null && !fabricacao.trim().equals("")) {
			s+= " - "+ fabricacao;
		}
		if(validade!=null && !validade.trim().equals("")) {
			s+= " - "+ validade;
		}
		return s;
	}
	
}