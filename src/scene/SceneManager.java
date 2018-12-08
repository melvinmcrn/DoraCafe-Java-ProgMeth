package scene;

import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.Stage;

public class SceneManager {

	private static Stage primaryStage;
	private static final int SCENE_WIDTH = 800;
	private static final int SCENE_HEIGHT = 600;
	private static WelcomeScene welcome = new WelcomeScene();
	private static HallOfFameScene highScore = new HallOfFameScene();
	private static CharacterSelectScene characterSelecet;
	private static GameScene game;

	private static Scene welcomeScene = new Scene(welcome, SCENE_WIDTH, SCENE_HEIGHT);
	private static Scene highScoreScene = new Scene(highScore, SCENE_WIDTH, SCENE_HEIGHT);
	private static Scene characterSelectScene;
	private static Scene gameScene;

	public static void initialize(Stage stage) {
		primaryStage = stage;
		primaryStage.show();
	}

	public static void gotoScene(String scene) {

		if (scene.equals("Welcome")) {
			if (WelcomeScene.getMiscPlayer().getStatus().equals(Status.PAUSED)
					|| WelcomeScene.getMiscPlayer().getStatus().equals(Status.STOPPED))
				WelcomeScene.getMiscPlayer().play();
			WelcomeScene.getVidPlayer().play();
			if (CharacterSelectScene.getVidPlayer().getStatus().equals(Status.PLAYING))
				CharacterSelectScene.getVidPlayer().pause();
			if (GameScene.getMiscPlayer().getStatus().equals(Status.PLAYING))
				GameScene.getMiscPlayer().stop();
			if (HallOfFameScene.getVidPlayer().getStatus().equals(Status.PLAYING))
				HallOfFameScene.getVidPlayer().pause();
			primaryStage.setScene(welcomeScene);
			primaryStage.show();
		} else if (scene.equals("Play")) {
			WelcomeScene.getVidPlayer().pause();
			CharacterSelectScene.getVidPlayer().play();
			characterSelecet = new CharacterSelectScene();
			characterSelectScene = new Scene(characterSelecet, SCENE_WIDTH, SCENE_HEIGHT);
			primaryStage.setScene(characterSelectScene);
			primaryStage.show();
		} else if (scene.equals("Hall of Fame")) {
			WelcomeScene.getVidPlayer().pause();
			HallOfFameScene.getVidPlayer().play();
			primaryStage.setScene(highScoreScene);
			primaryStage.show();
		} else if (scene.equals("Main Game")) {
			WelcomeScene.getVidPlayer().stop();
			WelcomeScene.getMiscPlayer().stop();
			HallOfFameScene.getVidPlayer().stop();
			CharacterSelectScene.getVidPlayer().stop();
			game = new GameScene();
			gameScene = new Scene(game, SCENE_WIDTH, SCENE_HEIGHT);
			primaryStage.setScene(gameScene);
			primaryStage.show();
		} else if (scene.equals("Exit")) {
			primaryStage.close();
		}
	}
}