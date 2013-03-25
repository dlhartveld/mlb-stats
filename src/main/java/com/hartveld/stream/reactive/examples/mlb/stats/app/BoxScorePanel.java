package com.hartveld.stream.reactive.examples.mlb.stats.app;

import static com.google.common.base.Preconditions.checkNotNull;

import com.hartveld.stream.reactive.examples.mlb.stats.client.BoxScore;
import com.hartveld.stream.reactive.examples.mlb.stats.client.Game;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BoxScorePanel extends JPanel {

	public final Game game;

	private final JLabel awayTeamErrors;
    private final JLabel awayTeamHits;
    private final JLabel awayTeamName;
    private final JLabel awayTeamRuns;
    private final JLabel homeTeamErrors;
    private final JLabel homeTeamHits;
    private final JLabel homeTeamName;
    private final JLabel homeTeamRuns;
    private final JLabel labelE;
    private final JLabel labelH;
    private final JLabel labelR;

	public BoxScorePanel(final Game game) {
		checkNotNull(game, "game");

		this.game = game;

		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		final BoxScore awayBoxScore = game.getBoxScoreA();
		final BoxScore homeBoxScore = game.getBoxScoreB();

		awayTeamName = new JLabel(awayBoxScore.getTeam().getName());
        homeTeamName = new JLabel(homeBoxScore.getTeam().getName());
        awayTeamRuns = new JLabel(Integer.toString(awayBoxScore.getRuns()));
		awayTeamRuns.setHorizontalAlignment(SwingConstants.CENTER);
        homeTeamRuns = new JLabel(Integer.toString(homeBoxScore.getRuns()));
		homeTeamRuns.setHorizontalAlignment(SwingConstants.CENTER);
        awayTeamHits = new JLabel(Integer.toString(awayBoxScore.getHits()));
		awayTeamHits.setHorizontalAlignment(SwingConstants.CENTER);
        homeTeamHits = new JLabel(Integer.toString(homeBoxScore.getHits()));
		homeTeamHits.setHorizontalAlignment(SwingConstants.CENTER);
        awayTeamErrors = new JLabel(Integer.toString(awayBoxScore.getErrors()));
		awayTeamErrors.setHorizontalAlignment(SwingConstants.CENTER);
        homeTeamErrors = new JLabel(Integer.toString(homeBoxScore.getErrors()));
		homeTeamErrors.setHorizontalAlignment(SwingConstants.CENTER);

		final int width = 14;
		final int height = 16;
		final Dimension dim = new Dimension(width, height);

		labelR = new JLabel("R");
        labelR.setHorizontalAlignment(SwingConstants.CENTER);
		labelR.setMinimumSize(dim);
		labelR.setPreferredSize(dim);
		labelR.setMaximumSize(dim);
        labelH = new JLabel("H");
        labelH.setHorizontalAlignment(SwingConstants.CENTER);
		labelH.setMinimumSize(dim);
		labelH.setPreferredSize(dim);
		labelH.setMaximumSize(dim);
        labelE = new JLabel("E");
        labelE.setHorizontalAlignment(SwingConstants.CENTER);
		labelE.setMinimumSize(dim);
		labelE.setPreferredSize(dim);
		labelE.setMaximumSize(dim);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(awayTeamName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(homeTeamName, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(homeTeamRuns, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(awayTeamRuns))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(homeTeamHits, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(awayTeamHits))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(awayTeamErrors, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(homeTeamErrors))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {awayTeamRuns, homeTeamRuns, labelR});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {awayTeamHits, homeTeamHits, labelH});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {awayTeamErrors, homeTeamErrors, labelE});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelR)
                    .addComponent(labelH)
                    .addComponent(labelE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(awayTeamName)
                    .addComponent(homeTeamRuns)
                    .addComponent(homeTeamHits)
                    .addComponent(awayTeamErrors))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(homeTeamName)
                    .addComponent(awayTeamRuns)
                    .addComponent(awayTeamHits)
                    .addComponent(homeTeamErrors))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
	}

}
