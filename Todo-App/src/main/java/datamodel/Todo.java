package datamodel;


import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="TodoList_Gangisetti")
public class Todo {
	
   @Column(name="id")
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Integer id;
   
   @Column(name="text")
   private String text;
   
   @Column(name="createdAt")
   private SimpleDateFormat createdAt;
   
   @Column(name="completed")
   private boolean completed;
   
   public Todo() {
	   super();
   }
   
   public Todo(String todo, boolean done) {
	   this.text = todo;
	   this.completed = done;
   }
   
   @PrePersist
   private void createdAt() {
	   this.createdAt = new SimpleDateFormat("MM/dd/yyyy");
   }
   
   public int getId() {
	   return id;
   }
   
   public String getTodo() {
	   return text;
   }
   
   public void setTodo(String todo) {
	   this.text = todo;
   }
   
   public void markCompleted(boolean done) {
	   this.completed = done;
   }
}
