-- 
select * from products;

insert into products (product_name, price, description, category_id, stock, discount) 
values ("MALLA SOMBRA 95% ESTRUCTURAL", 2000, "Malla especialmente diseñada para adaptarse a tenso estructuras dando un toque vanguardista al entorno.", 3, 60, 0.10),
	   ("CANALETA HIDROPÓNICA", 2755,"Proporciona un total aislamiento suelo-raíz; permite economizar agua, fertilizantes, agroquímicos y mano de obra", 4, 100, 0.20),
       ("ALAMBRE ZIGZAG PLASTIFICADO AZUL VINIPET® B", 1600, "Sujeta el plástico dentro del perfil cortinero o poligrap.", 1, 80, 0.10),
       ("CONECTOR PARA MALLA CON PUENTE 1,000 PZAS", 197.56, "El conector para malla con puente es la mejor manera de limitar el daño a la estructura y a la malla",2, 180,0.10),
       ("MACETA RÍGIDA NEGRA 11 LITROS", 45, "Contenedor duradero para cultivo individual de plantas.", 4, 1000,0.20);  ;
          