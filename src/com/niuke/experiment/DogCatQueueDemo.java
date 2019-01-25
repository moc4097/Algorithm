package com.niuke.experiment;

import java.util.LinkedList;
import java.util.Queue;

import javax.management.RuntimeErrorException;

public class DogCatQueueDemo {

	public class Pet{
		private String type;
		
		public Pet(String type) {
			this.type=type;
		}
		
		public String getPetType() {
			return this.type;
		}
	}
	
	public class Dog extends Pet{
		public Dog() {
			super("dog");
		}
	}
	
	public class Cat extends Pet{
		public Cat() {
			super("cat");
		}
	}
	
	public class PetEntryQueue{
		private Pet pet;
		private long index;
		
		public PetEntryQueue(Pet pet,long count) {
			this.pet=pet;
			this.index=count;
		}

		public Pet getPet() {
			return pet;
		}

		public void setPet(Pet pet) {
			this.pet = pet;
		}

		public long getIndex() {
			return index;
		}

		public void setIndex(long index) {
			this.index=index;
		}
		
		public String getPetEntryType() {
			return this.getPet().getPetType();
		}
	}
	
	public class DogCatQueue{
		private Queue<PetEntryQueue> dogQ;
		private Queue<PetEntryQueue> catQ;
		private long index;
		
		public DogCatQueue() {
			dogQ=new LinkedList<PetEntryQueue>();
			catQ=new LinkedList<PetEntryQueue>();
			index=0;
		}
		
		public void add(Pet pet) {
			if(pet.getPetType().equals("dog")) {
				dogQ.offer(new PetEntryQueue(pet,index++));
			}else if(pet.getPetType().equals("cat")) {
				catQ.offer(new PetEntryQueue(pet,index++));
			}else {
				throw new RuntimeException("error,not dog or cat");
			}
		}
		
		public Pet pollAll() {
			if(!isDogEmpty()&&!isCatEmpty()) {
				if(dogQ.peek().getIndex()<catQ.peek().getIndex()) {
					return (Dog)dogQ.poll().getPet();
				}else {
					return (Cat)catQ.poll().getPet();
				}
			}else if(!isDogEmpty()) {
				return (Dog)dogQ.poll().getPet();
			}else if(!isCatEmpty()) {
				return (Cat)catQ.poll().getPet();
			}else {
				throw new RuntimeException("error,queue is empty");
			}
		}
		
		public Dog pollDog() {
			if(!isDogEmpty()) {
				return (Dog)dogQ.poll().getPet();
			}else {
				throw new RuntimeException("Dog queue is empty");
			}
		}
		
		public Cat pollCat() {
			if(!isCatEmpty()) {
				return (Cat)catQ.poll().getPet();
			}else {
				throw new RuntimeException("Cat queue is empty");
			}
		}
		
		public boolean isEmpty() {
			if(dogQ.isEmpty()&&catQ.isEmpty()) {
				return true;
			}
			return false;
		}
		
		public boolean isDogEmpty() {
			if(dogQ.isEmpty()) {
				return true;
			}
			return false;
		}
		
		public boolean isCatEmpty() {
			if(catQ.isEmpty()) {
				return true;
			}
			return false;
		}
	}
	
	public static void main(String[] args) {
		DogCatQueueDemo dcqd=new DogCatQueueDemo();
		DogCatQueue dcq=dcqd.new DogCatQueue();
		
		Pet dog1=dcqd.new Dog();
		Pet dog2=dcqd.new Dog();
		Pet dog3=dcqd.new Dog();
		Pet cat1=dcqd.new Cat();
		Pet cat2=dcqd.new Cat();
		Pet cat3=dcqd.new Cat();
		
		dcq.add(dog1);
		dcq.add(dog2);
		dcq.add(dog3);
		dcq.add(cat1);
		dcq.add(cat2);
		dcq.add(cat3);
		
		System.out.println(dcq.pollAll().getPetType());
		System.out.println(dcq.pollCat());
	}
}
