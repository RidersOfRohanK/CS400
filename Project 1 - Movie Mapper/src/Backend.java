// --== CS400 File Header Information ==--
// Name: Peilin Lu/Rohan Kale
// Email: plu39@wisc.edu / rkale2@wisc.edu
// Team: blue / red
// Role: Backend Developer / Frontend Developer
// TA: Yelun
// Lecturer: Gary
// Notes to Grader: Rohan Kale edited this because of a whole series of unfortunate events that red teams integration
//manager told the TA about. Rohan also wrote a getAllRatings method to help his front end.

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Backend implements BackendInterface {
    List<MovieInterface> dataset;
    ArrayList<String> genres;
	ArrayList<String> ratings;
	HashTableMap<String, ArrayList<MovieInterface>> map;

    public Backend(List<MovieInterface> dataset) {
		this.dataset = dataset;
		genres = new ArrayList<String>();
		ratings = new ArrayList<String>();
		map = new HashTableMap<String, ArrayList<MovieInterface>>();
		for (MovieInterface movie : dataset) {
			for (String genre : movie.getGenres()) {
				String rating = Integer.toString((int) movie.getAvgVote().floatValue());
				String key = genre + "\0" + rating;
				ArrayList<MovieInterface> bin;
				try {
					bin = map.get(key);
				} catch (NoSuchElementException e) {
					bin = new ArrayList<MovieInterface>();
					map.put(key, bin);
				}
				bin.add(movie);
			}
		}
    }
    	
	public void addGenre(String genre) {
        if (!genres.contains(genre)) genres.add(genre);
    }
	public void addAvgRating(String rating) {
		if (!ratings.contains(rating)) ratings.add(rating);
	}

	public void removeGenre(String genre) {
		genres.remove(genre);
	}

	public void removeAvgRating(String rating) {
		ratings.remove(rating);
	}

	public List<String> getGenres() {
		return genres;
	}

	public List<String> getAvgRatings() {
		return ratings;
	}

	List<MovieInterface> getMovies() {

		ArrayList<MovieInterface> movies = new ArrayList<MovieInterface>();
		for (String rating : getAvgRatings()) {
			for (String genre : getGenres()) {
					String key = genre + "\0" + rating;
					ArrayList<MovieInterface> bin;
					try {
					bin = map.get(key);
					for (MovieInterface movie : bin) {
						if (!movies.contains(movie)) movies.add(movie);
					}
				} catch (NoSuchElementException e) {
					// pass
				}
			}
		}
		return movies;
	}

	public int getNumberOfMovies() {
		return getMovies().size();
	}

	public List<String> getAllGenres() {
		ArrayList<String> genres = new ArrayList<String>();
		for (MovieInterface movie : dataset) {
			for (String genre : movie.getGenres()) {
				if (!genres.contains(genre)) genres.add(genre);
			}
		}
		return genres;
	}
	public List<String> getAllRatings(){
		ArrayList<String> allRatings = new ArrayList<String>();
		for (int i = 0; i < 11; i++) {
			 allRatings.add(Integer.toString(i));
		}
		return allRatings;
	}//This is Rohan - I added this to help my frontend implementation be similar to genres just for consistency

	public List<MovieInterface> getThreeMovies(int startingIndex) {
		ArrayList<MovieInterface> selected = new ArrayList<MovieInterface>();
		List<MovieInterface> movies = getMovies();
		for (int i=0; i<3; i++) {
			int ii = i + startingIndex;
			if (ii >= 0 && ii < movies.size()) {
				selected.add(movies.get(ii));
			}
		}
		return selected;
	}
}