package sgf.controller.game;

import java.awt.Dimension;

import sgf.managers.GameManager;
import sgf.managers.PlayerManager;
import sgf.model.turret.Turret;
import sgf.view.game.AbstractGameView;
import sgf.view.game.AbstractPlayingView;
import sgf.view.game.PlayingView;

/**
 * 
 */
public class PlayingControllerImpl implements PlayingController {

    private AbstractPlayingView playingView;
    private final GameManager gameManager;
    private final PlayerManager playerManager;
    private boolean isControllerSet;

    /**
     * 
     * @param gameManager
     * @param playerManager
     */
    public PlayingControllerImpl(final GameManager gameManager, final PlayerManager playerManager) {
        this.gameManager = gameManager;
        this.playerManager = playerManager;
    }

    @Override
    public GameManager getGameManager() {
        return this.gameManager;
    }

    @Override
    public PlayerManager getPlayerManager() {
        return this.playerManager;
    }

    @Override
    public Dimension getViewSize() {
        if (this.playingView != null) {
            return this.playingView.getSize();
        } else {
            return new Dimension();
        }
    }

    @Override
    public void showUpgrades(final Turret t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void hideUpgrades() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean buy(final Turret t) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean upgradeTurret(final Turret upgrade) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setView(final PlayingView view) {
        if (!isControllerSet) {
            if (view instanceof AbstractPlayingView) {
                this.isControllerSet = true;
                this.playingView = (AbstractPlayingView) view;
            } else {
                throw new IllegalArgumentException("Argument must be subclass of AbstractPlayingView");
            }
        }
    }
}