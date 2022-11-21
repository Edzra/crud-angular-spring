export interface Product {
  _id: number; // Primary key for the database.
  _idCategory: number; // Foreign key for the auxiliary Categories table on the database.
  description: string;
  purchaseDate: string;
  price: number;
}
