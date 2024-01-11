package com.codewithjava21.movieapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import com.codewithjava21.movieapp.service.Movie;
import com.codewithjava21.movieapp.service.MovieAppController;
import com.codewithjava21.movieapp.service.MovieByTitleRepository;
import com.codewithjava21.movieapp.service.MovieRepository;
import com.google.common.io.Files;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

@Route("")
public class MovieAppMainView extends VerticalLayout {

	private static final long serialVersionUID = -1773398995755184441L;
	
	private TextField queryField = new TextField();
	private RadioButtonGroup<String> queryBy = new RadioButtonGroup<>();
	
	private Button queryButton;
	private Button upButton;
	
	private Image image = new Image();
	private Image recImage1 = new Image();
	private Image recImage2 = new Image();
	private Image recImage3 = new Image();
	private Image recImage4 = new Image();
	private Image recImage5 = new Image();
	
	private Span genre1 = new Span();
	private Span genre2 = new Span();
	private Span genre3 = new Span();

	private TextField movieId = new TextField("ID");
	private TextField releaseDate = new TextField("release date");
	private TextField website = new TextField("website");
	private TextField imdbWebsite = new TextField("IMDB website");
	private TextField imdb = new TextField("IMDB");
	private TextField language = new TextField("original language");
	private TextField budget = new TextField("budget");
	private TextField revenue = new TextField("revenue");
	private TextField voteRating = new TextField("rating");
	private TextField votes = new TextField("total votes");
	
	private Paragraph description = new Paragraph();
	private Paragraph title = new Paragraph();
	private Paragraph recommendation1 = new Paragraph();
	private Paragraph recommendation2 = new Paragraph();
	private Paragraph recommendation3 = new Paragraph();
	private Paragraph recommendation4 = new Paragraph();
	private Paragraph recommendation5 = new Paragraph();
	private Paragraph year = new Paragraph();
	
	private Locale enUS = Locale.US;
	private MemoryBuffer buffer;
	private Pattern numericPattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	private Upload upload;
	private String noImageFile = "images/noImage.png";
	private StreamResource noImgFileStream;
	private Map<Integer,String> mapGenres = new HashMap<>();
	
	private MovieAppController controller;
	
	public MovieAppMainView(MovieRepository mRepo, MovieByTitleRepository mtRepo) {
		controller = new MovieAppController(mRepo, mtRepo);
		
		add(buildQueryBar());
		add(buildTitle());

		add(buildImageUpdateControls());
		add(buildImageData(), description);
		add(buildGenreData());
		
		//add(description);
		add(buildMovieMetaData());
		add(buildFinancialData());
		add(buildRatingData());
		add(buildWebsiteData());
		add(new Paragraph("You may also enjoy these similar titles:"));
		add(buildRecommendations());
	}
	
	private Component buildQueryBar() {
		HorizontalLayout layout = new HorizontalLayout();
		
		queryButton = new Button("Query");
		queryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		Icon search = new Icon(VaadinIcon.SEARCH);
		queryField.setPrefixComponent(search);
		
		layout.add(queryField, queryButton, buildQueryRadio());
		
		queryButton.addClickListener(click -> {
			refreshData();
		});
		
		return layout;
	}
	
	private Component buildTitle() {
		HorizontalLayout layout = new HorizontalLayout();

		title.getStyle()
				.set("font-weight", "bold")
				.set("font-size", "x-large");
		
		layout.add(title, year);
		
		return layout;
	}
		
	private Component buildQueryRadio() {
		HorizontalLayout layout = new HorizontalLayout();

		queryBy.setLabel("Query by:");
		queryBy.setItems("ID", "Title");
		queryBy.setValue("ID");
		
		layout.add(queryBy);
		
		return layout;		
	}
	
	private Component buildGenreData() {
		HorizontalLayout layout = new HorizontalLayout();

		genre1.getElement().getThemeList().add("badge");
		genre1.setVisible(false);
		
		genre2.getElement().getThemeList().add("badge");
		genre2.setVisible(false);
		
		genre3.getElement().getThemeList().add("badge");
		genre3.setVisible(false);

		layout.add(genre1, genre2, genre3);
		
		return layout;
	}

	private Component buildImageData() {
		HorizontalLayout layout = new HorizontalLayout();
		
		try {
			FileInputStream fileStream = new FileInputStream(new File(noImageFile));
			noImgFileStream = new StreamResource("image",() -> {
				return fileStream;
			});
			image.setSrc(noImgFileStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getImage(-1);
		image.setHeight("300px");
		layout.add(image);
		
		return layout;
	}
	
	private Component buildImageUpdateControls() {
		HorizontalLayout layout = new HorizontalLayout();

		upButton = new Button("Upload JPG or PNG");
		upButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		buffer = new MemoryBuffer();
		upload = new Upload(buffer);
		upload.setUploadButton(upButton);
		upload.setAcceptedFileTypes("image/png", "image/jpg", "image/jpeg");
		upload.addSucceededListener(event -> {
			// generate filename
			int movieID = Integer.parseInt(movieId.getValue());
			StringBuilder filename = new StringBuilder("images/");
			String mimeType = event.getMIMEType();
			
			filename.append("movie_");
			filename.append(movieId.getValue());
						
			if (mimeType.equals("image/jpg") || mimeType.equals("image/jpeg")) {
				filename.append(".jpg");
			} else {
				// we only accept jpegs or pngs, so it must be a png
				filename.append(".png");
			}
			
			InputStream inStream = buffer.getInputStream();
			try {
				// get file from memory
				byte[] byteBuffer = new byte[inStream.available()];
				inStream.read(byteBuffer);
				
				// write to disk
				File destination = new File(filename.toString());
				Files.write(byteBuffer, destination);
				//inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// display new image
			getImage(movieID);
			upload.clearFileList();
		});

		layout.add(upload);

		return layout;
	}
	
	private void getImage(int movieID) {
		StreamResource src = getImageStream(movieID);
		image.setSrc(src);
	}

	private StreamResource getImageStream(int movieID) {
		StringBuilder filename = new StringBuilder("images/"); 
		
		if (movieID >= 0) {
			filename.append("movie_");
			filename.append(movieID);
			filename.append(".jpg");
			// try jpg first
			
			if (!new File(filename.toString()).exists()) {
				// try png next
				filename = new StringBuilder("images/");
				filename.append("movie_");
				filename.append(movieID);
				filename.append(".png");
			}
			
			try {
				FileInputStream imgFileStream = new FileInputStream(new File(filename.toString()));
				StreamResource src = new StreamResource("image",() -> {
					return imgFileStream;
				});
				return src;
			} catch (FileNotFoundException ex) {
				// file not found; set to "No Image" file stream
				return noImgFileStream;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return noImgFileStream;
	}
	
	private Component buildMovieMetaData() {
		HorizontalLayout layout = new HorizontalLayout();

		movieId.setReadOnly(true);
		imdb.setReadOnly(true);
		language.setReadOnly(true);
		
		layout.add(movieId, imdb, language);
				
		return layout;
	}
	
	private Component buildFinancialData() {
		HorizontalLayout layout = new HorizontalLayout();

		releaseDate.setReadOnly(true);
		budget.setReadOnly(true);
		revenue.setReadOnly(true);
		
		layout.add(releaseDate, budget, revenue);
		

		return layout;
	}
	
	private Component buildRatingData() {
		HorizontalLayout layout = new HorizontalLayout();

		voteRating.setReadOnly(true);
		votes.setReadOnly(true);
		
		Icon star = new Icon(VaadinIcon.STAR);
		star.setColor("gold");
		voteRating.setPrefixComponent(star);
		
		layout.add(voteRating, votes);
		
		return layout;
	}
	
	private Component buildWebsiteData() {
		HorizontalLayout layout = new HorizontalLayout();
		
		website.setReadOnly(true);
		website.setWidth("400px");
		imdbWebsite.setReadOnly(true);
		imdbWebsite.setWidth("400px");
		
		layout.add(website, imdbWebsite);
		
		return layout;
	}
	
	private Component buildRecommendations() {
		HorizontalLayout layout = new HorizontalLayout();

		VerticalLayout vLayout1 = new VerticalLayout();
		recImage1.setHeight("100px");
		recImage1.addClickListener( event -> {
			String[] movieText = recommendation1.getText().split(" - ");
			queryField.setValue(movieText[0]);
			queryBy.setValue("ID");
			refreshData();
		});
		recommendation1.getStyle()
			.set("font-size", "x-small");
		vLayout1.add(recImage1,recommendation1);
		
		VerticalLayout vLayout2 = new VerticalLayout();
		recImage2.setHeight("100px");
		recImage2.addClickListener( event -> {
			String[] movieText = recommendation2.getText().split(" - ");
			queryField.setValue(movieText[0]);
			queryBy.setValue("ID");
			refreshData();
		});
		recommendation2.getStyle()
			.set("font-size", "x-small");
		vLayout2.add(recImage2,recommendation2);

		VerticalLayout vLayout3 = new VerticalLayout();
		recImage3.setHeight("100px");
		recImage3.addClickListener( event -> {
			String[] movieText = recommendation3.getText().split(" - ");
			queryField.setValue(movieText[0]);
			queryBy.setValue("ID");
			refreshData();
		});
		recommendation3.getStyle()
			.set("font-size", "x-small");
		vLayout3.add(recImage3,recommendation3);

		VerticalLayout vLayout4 = new VerticalLayout();
		recImage4.setHeight("100px");
		recImage4.addClickListener( event -> {
			String[] movieText = recommendation4.getText().split(" - ");
			queryField.setValue(movieText[0]);
			queryBy.setValue("ID");
			refreshData();
		});
		recommendation4.getStyle()
			.set("font-size", "x-small");
		vLayout4.add(recImage4,recommendation4);

		VerticalLayout vLayout5 = new VerticalLayout();
		recImage5.setHeight("100px");
		recImage5.addClickListener( event -> {
			String[] movieText = recommendation5.getText().split(" - ");
			queryField.setValue(movieText[0]);
			queryBy.setValue("ID");
			refreshData();
		});
		recommendation5.getStyle()
			.set("font-size", "x-small");
		vLayout5.add(recImage5,recommendation5);

		layout.add(vLayout1,vLayout2,vLayout3,vLayout4,vLayout5);

		return layout;
	}
	
	private String formatMoney(Long money) {
		
		NumberFormat numFormat = NumberFormat.getCurrencyInstance(enUS);
		return numFormat.format(Double.parseDouble(money.toString()));
	}
	
	private void refreshData() {
	
		Optional<Movie> optionalMovie;
		
		if (queryBy.getValue().equals("ID")) {
			if (isNumeric(queryField.getValue())) {
				optionalMovie = controller.getMovieByMovieId(
						Integer.parseInt(queryField.getValue()))
						.getBody();
			} else {
				optionalMovie = Optional.ofNullable(null);
			}
		} else {
			// name
			optionalMovie = controller.getMovieByTitle(queryField.getValue()).getBody();
		}
		
		if (optionalMovie != null && optionalMovie.isPresent()) {

			Movie movie = optionalMovie.get();
			
			String strTitle = movie.getTitle();
			String strDescription = movie.getDescription();
			LocalDate ldReleaseDate = movie.getReleaseDate();
			Integer intYear = movie.getYear();
			mapGenres = movie.getGenres();
			String strWebsiteUrl = movie.getWebsite();
			String strImdbId = movie.getImdbId();
			String strLanguage = movie.getOriginalLanguage();
			
			title.setText(strTitle);
			description.setText(strDescription);
			
			if (movie.getMovieId() != null) {
				movieId.setValue(movie.getMovieId().toString());
				// once we have the ID, get the image
				getImage(movie.getMovieId());
			}
			
			if (movie.getReleaseDate() != null) {
				releaseDate.setValue(ldReleaseDate.toString());
			}
	
			if (intYear != null) {
				year.setText(intYear.toString());
			}
	
			// process genre "badges"
			int genreCounter = 0;
			genre1.setVisible(false);
			genre2.setVisible(false);
			genre3.setVisible(false);
	
			for (String genre : mapGenres.values()) {
	
				switch (genreCounter) {
				case 0:
					genre1.setText(genre);;
					genre1.setVisible(true);
					break;
				case 1:
					genre2.setText(genre);
					genre2.setVisible(true);
					break;
				case 2:
					genre3.setText(genre);
					genre3.setVisible(true);
					break;
				default:
					break;
				}
	
				genreCounter++;
			}
			
			website.setValue(strWebsiteUrl);
			imdbWebsite.setValue("https://www.imdb.com/title/" + strImdbId);
			imdb.setValue(strImdbId);
			language.setValue(strLanguage);
			
			if (movie.getBudget() != null) {
				budget.setValue(formatMoney(movie.getBudget()));
			}
			
			if (movie.getRevenue() != null) {
				revenue.setValue(formatMoney(movie.getRevenue()));
			}
			
			if (movie.getVector() != null) {
				voteRating.setValue(movie.getVector().get(5).toString());
				votes.setValue(movie.getVector().get(6).toString());
			}
			

			if (movieId.getValue().length() > 0) {
				Integer movieID = Integer.parseInt(movieId.getValue());
				
				List<Movie> recommendedMovies = controller.getMovieRecommendationsById(movieID).getBody();
				
				int movieCounter = 0;
				for (Movie recMovie : recommendedMovies) {
					StreamResource resource = getImageStream(recMovie.getMovieId());
					StringBuilder titleText = new StringBuilder(recMovie.getMovieId().toString());
					titleText.append(" - ");
					titleText.append(recMovie.getTitle());
					
					switch (movieCounter) {
					case 0:
						recImage1.setSrc(resource);
						recommendation1.setText(titleText.toString());
						break;
					case 1:
						recImage2.setSrc(resource);
						recommendation2.setText(titleText.toString());
						break;
					case 2:
						recImage3.setSrc(resource);
						recommendation3.setText(titleText.toString());
						break;
					case 3:
						recImage4.setSrc(resource);
						recommendation4.setText(titleText.toString());
						break;
					default:
						recImage5.setSrc(resource);
						recommendation5.setText(titleText.toString());
					}
					movieCounter++;
					
					if (movieCounter > 4) {
						break;
					}
				}
			}
		} else {
			Notification.show("No movie found for those query parameters.",
					5000, Position.TOP_CENTER);
		}
	}
	
	// https://www.baeldung.com/java-check-string-number
	// numericPattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	private boolean isNumeric(String value) {
		if (value == null) {
			return false;
		}
		
		return numericPattern.matcher(value).matches();
	}
}
