package datamodel;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
   private String createdAt;
   
   @Column(name="completed")
   private String completed;
   
   public Todo() {
	   super();
   }
   
   public Todo(String todo, String done) {
	   this.text = todo;
	   this.completed = done;
       this.createdAt = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
   }
      
   public int getId() {
	   return id;
   }
   
   public String getText() {
	   return text;
   }
   
   public String getCreatedAt() {
	   return createdAt;
   }
   
   public String getCompleted() {
	   return completed;
   }
   
   public void setTodo(String todo) {
	   this.text = todo;
   }
   
   public void markCompleted(String done) {
	   this.completed = done;
   }
      
}
