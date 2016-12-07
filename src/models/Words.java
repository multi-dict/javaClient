package models;
import java.util.ArrayList;

public class Words {
	private ArrayList<Word> words;
	private int entityID;
	public Words (ArrayList<Word> w,Entity e){
		this.words=w;
		this.entityID=e.GetID();
	}
	
	@Override
	public String toString(){
		String result = "";
		if(this.words == null){
			return "";
		}
		for (int i = 0; i<this.words.size(); i++){
			if(i==0){
				result=this.words.get(i).GetWord();
			}
			else{
				result = result + ", " + this.words.get(i).GetWord();
			}
		}
		return result;
	}

	public String getToolTip() {
		String result = "";
		if(this.words == null){
			return "";
		}
		for (int i = 0; i<this.words.size(); i++){
			if(i==0){
				result=this.words.get(i).GetDescription();
			}
			else{
				result = result + ", " + this.words.get(i).GetDescription();
			}
		}
		return result;
	}

	public boolean Contains(String filter) {
		for(Word w : this.words){
			if(w.GetWord().contains(filter)){
				return true;
			}
		}
		return false;
	}

	public ArrayList<Word> getWords() {
		return this.words;
	}
	public int GetEntityID(){
		return this.entityID;
	}
}
