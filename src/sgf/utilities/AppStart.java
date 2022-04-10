package sgf.utilities;
import java.util.List;

import javax.swing.JPanel;

import sgf.controller.enemy.EnemyController;
import sgf.controller.enemy.EnemyControllerImpl;
import sgf.controller.game.GameController;
import sgf.controller.game.GameControllerImpl;
import sgf.controller.game.MusicController;
import sgf.controller.game.MusicControllerImpl;
import sgf.controller.game.PlayingController;
import sgf.controller.game.PlayingControllerImpl;
import sgf.controller.map.MapController;
import sgf.controller.map.MapControllerImpl;
import sgf.controller.menu.MenuController;
import sgf.controller.menu.MenuControllerImpl;
import sgf.controller.shop.ShopController;
import sgf.controller.shop.ShopControllerImpl;
import sgf.helpers.MapLoaderImpl;
import sgf.helpers.WavesLoaderImpl;
import sgf.managers.GameManager;
import sgf.managers.LevelManager;
import sgf.managers.LevelManagerImpl;
import sgf.managers.PlayerManager;
import sgf.managers.PlayerManagerImpl;
import sgf.model.game.Classification;
import sgf.model.game.ClassificationImpl;
import sgf.model.game.Player;
import sgf.model.game.PlayerImpl;
import sgf.model.level.Level;
import sgf.model.level.LevelImpl;
import sgf.model.level.Wave;
import sgf.model.map.Map;
import sgf.view.ScreenGame;
import sgf.view.enemy.AbstractEnemyView;
import sgf.view.enemy.EnemyViewImpl;
import sgf.view.game.AbstractGameView;
import sgf.view.game.AbstractPlayingView;
import sgf.view.game.GameViewImpl;
import sgf.view.game.PlayingViewImpl;
import sgf.view.map.AbstractMapView;
import sgf.view.map.MapViewImpl;
import sgf.view.menu.AbstractMenuView;
import sgf.view.menu.MenuView;
import sgf.view.menu.MenuViewImpl;
import sgf.view.shop.AbstractShopView;
import sgf.view.shop.ShopViewImpl;

/**
 *
 */
public final class AppStart {

    private AppStart() {
    }

    /**
     * 
     * @param args
     */
    public static void main(final String[] args) {
        final GameManager gameManager = null;

        Classification cl = new ClassificationImpl();
        final MusicController m = new MusicControllerImpl();

        final Map map = new MapLoaderImpl(1).getMap();  // 1 to be generalized.
        final MapController mapController = new MapControllerImpl(map);
        final List<Wave> waves = new WavesLoaderImpl(map, 1).getWaves();      // 1 to be generalized.
        final Level level = new LevelImpl(waves, map);
        final LevelManager levelManager = new LevelManagerImpl(level);
        final Player player = new PlayerImpl();
        final PlayerManager playerManager = new PlayerManagerImpl(player);

        /*
         * At the start only the menu, settings and levels view will be created.
         * All these other views and controllers will be created when someone clicks on a level.
         */
        final AbstractMapView mapView = new MapViewImpl(map);
        final EnemyController enemyController = new EnemyControllerImpl(levelManager, playerManager);
        final AbstractEnemyView enemyView = new EnemyViewImpl(map.getSize());
        final GameController gameController = new GameControllerImpl();
        final AbstractGameView gameView = new GameViewImpl(mapView, enemyView);
        final ShopController shopController = new ShopControllerImpl(gameManager);
        final AbstractShopView shopView = new ShopViewImpl(gameManager);
        final PlayingController playingController = new PlayingControllerImpl(gameManager, playerManager);
        final AbstractPlayingView playingView = new PlayingViewImpl(gameView, shopView);

        //AbstractMenuView menuView = new MenuViewImpl();
        //MenuController menuController = new MenuControllerImpl();

        /**
         * Linking.
         */
        gameController.setView(gameView);
        gameView.setController(gameController);
        mapController.setView(mapView);
        mapView.setController(mapController);
        enemyController.setView(enemyView);
        enemyView.setController(enemyController);
        shopController.setView(shopView);
        shopView.setController(shopController);
        playingController.setView(playingView);
        playingView.setController(playingController);
        //menuController.setView(menuView);
        //menuView.setController(menuController);

        shopView.start();
        mapView.start();
        enemyView.start();
        gameView.start();
        playingView.start();
        //menuView.start();


        //new ScreenGame(playingView);
    }
}
