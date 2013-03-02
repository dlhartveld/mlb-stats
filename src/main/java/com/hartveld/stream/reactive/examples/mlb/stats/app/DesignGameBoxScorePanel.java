package com.hartveld.stream.reactive.examples.mlb.stats.app;

public class DesignGameBoxScorePanel extends javax.swing.JPanel {

	public DesignGameBoxScorePanel() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        awayTeamName = new javax.swing.JLabel();
        homeTeamName = new javax.swing.JLabel();
        homeTeamRuns = new javax.swing.JLabel();
        awayTeamRuns = new javax.swing.JLabel();
        homeTeamHits = new javax.swing.JLabel();
        awayTeamHits = new javax.swing.JLabel();
        awayTeamErrors = new javax.swing.JLabel();
        homeTeamErrors = new javax.swing.JLabel();
        labelR = new javax.swing.JLabel();
        labelH = new javax.swing.JLabel();
        labelE = new javax.swing.JLabel();

        jLabel6.setText("jLabel6");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        awayTeamName.setText("Away Team");

        homeTeamName.setText("Home Team");

        homeTeamRuns.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        homeTeamRuns.setText("00");

        awayTeamRuns.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        awayTeamRuns.setText("00");

        homeTeamHits.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        homeTeamHits.setText("00");

        awayTeamHits.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        awayTeamHits.setText("00");

        awayTeamErrors.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        awayTeamErrors.setText("00");

        homeTeamErrors.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        homeTeamErrors.setText("00");

        labelR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelR.setText("R");

        labelH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelH.setText("H");

        labelE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelE.setText("E");

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
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel awayTeamErrors;
    private javax.swing.JLabel awayTeamHits;
    private javax.swing.JLabel awayTeamName;
    private javax.swing.JLabel awayTeamRuns;
    private javax.swing.JLabel homeTeamErrors;
    private javax.swing.JLabel homeTeamHits;
    private javax.swing.JLabel homeTeamName;
    private javax.swing.JLabel homeTeamRuns;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelE;
    private javax.swing.JLabel labelH;
    private javax.swing.JLabel labelR;
    // End of variables declaration//GEN-END:variables
}
