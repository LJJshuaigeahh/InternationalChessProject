package model;

import controller.ClickController;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KingChessComponent extends ChessComponent {

    private static Image KING_WHITE;
    private static Image KING_BLACK;

    private Image kingImage;

    public void loadResource() throws IOException {
        if (KING_WHITE == null) {
            KING_WHITE = ImageIO.read(new File("./images/king-white.png"));
        }

        if (KING_BLACK == null) {
            KING_BLACK = ImageIO.read(new File("./images/king-black.png"));
        }
    }

    private void initiateRookImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                kingImage = KING_WHITE;
            } else if (color == ChessColor.BLACK) {
                kingImage = KING_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KingChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateRookImage(color);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if (getChessColor() == ChessColor.BLACK) {
            if ((Math.abs(destination.getX() - source.getX()) == 1 && Math.abs(destination.getY() - source.getY()) == 1) || (Math.abs(destination.getX() - source.getX()) == 1 && Math.abs(destination.getY() - source.getY()) == 0) || (Math.abs(destination.getX() - source.getX()) == 0 && Math.abs(destination.getY() - source.getY()) == 1)) {
                boolean test = true;
                out:
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        ChessComponent[][] chessComponents1 = chessComponents;
                        chessComponents1[destination.getX()][destination.getY()] = new KingChessComponent(getChessboardPoint(), getLocation(), getChessColor(), getClickController(), 0);
                        if (chessComponents1[i][j].getChessColor() == ChessColor.WHITE && chessComponents1[i][j].canMoveTo(chessComponents, destination)) {
                            test = false;
                            break out;
                        }
                    }
                }
                return test;
            } else {
                return false;
            }
        } else {
            if ((Math.abs(destination.getX() - source.getX()) == 1 && Math.abs(destination.getY() - source.getY()) == 1) || (Math.abs(destination.getX() - source.getX()) == 1 && Math.abs(destination.getY() - source.getY()) == 0) || (Math.abs(destination.getX() - source.getX()) == 0 && Math.abs(destination.getY() - source.getY()) == 1)) {
                boolean test = true;
                out:
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (chessComponents[i][j].getChessColor() == ChessColor.BLACK && chessComponents[i][j].canMoveTo(chessComponents, destination)) {
                            test = false;
                            break out;
                        }
                    }
                }
                return test;
            } else {
                return false;
            }
        }
    }

    public List<ChessboardPoint> canMoveToList(ChessComponent[][] chessComponents) {
        ArrayList<ChessboardPoint> canMoveTo = new ArrayList<>();
        if (getChessColor() == ChessColor.BLACK) {
            if (getChessboardPoint().offset(1, 1) != null) {
                if (chessComponents[getChessboardPoint().getX() + 1][getChessboardPoint().getY() + 1] instanceof EmptySlotComponent) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX() + 1, getChessboardPoint().getY() + 1));
                } else if (chessComponents[getChessboardPoint().getX() + 1][getChessboardPoint().getY() + 1].getChessColor() == ChessColor.WHITE) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX() + 1, getChessboardPoint().getY() + 1));
                }
            }
            if (getChessboardPoint().offset(1, -1) != null) {
                if (chessComponents[getChessboardPoint().getX() + 1][getChessboardPoint().getY() - 1] instanceof EmptySlotComponent) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX() + 1, getChessboardPoint().getY() - 1));
                } else if (chessComponents[getChessboardPoint().getX() + 1][getChessboardPoint().getY() - 1].getChessColor() == ChessColor.WHITE) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX() + 1, getChessboardPoint().getY() - 1));
                }
            }
            if (getChessboardPoint().offset(-1, 1) != null) {
                if (chessComponents[getChessboardPoint().getX() - 1][getChessboardPoint().getY() + 1] instanceof EmptySlotComponent) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX() - 1, getChessboardPoint().getY() + 1));
                } else if (chessComponents[getChessboardPoint().getX() - 1][getChessboardPoint().getY() + 1].getChessColor() == ChessColor.WHITE) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX() - 1, getChessboardPoint().getY() + 1));
                }
            }
            if (getChessboardPoint().offset(-1, -1) != null) {
                if (chessComponents[getChessboardPoint().getX() - 1][getChessboardPoint().getY() - 1] instanceof EmptySlotComponent) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX() - 1, getChessboardPoint().getY() - 1));
                } else if (chessComponents[getChessboardPoint().getX() - 1][getChessboardPoint().getY() - 1].getChessColor() == ChessColor.WHITE) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX() - 1, getChessboardPoint().getY() - 1));
                }
            }
            if (getChessboardPoint().offset(1, 0) != null) {
                if (chessComponents[getChessboardPoint().getX() + 1][getChessboardPoint().getY()] instanceof EmptySlotComponent) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX() + 1, getChessboardPoint().getY()));
                } else if (chessComponents[getChessboardPoint().getX() + 1][getChessboardPoint().getY()].getChessColor() == ChessColor.WHITE) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX() + 1, getChessboardPoint().getY()));
                }
            }
            if (getChessboardPoint().offset(-1, 0) != null) {
                if (chessComponents[getChessboardPoint().getX() - 1][getChessboardPoint().getY()] instanceof EmptySlotComponent) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX() - 1, getChessboardPoint().getY()));
                } else if (chessComponents[getChessboardPoint().getX() - 1][getChessboardPoint().getY()].getChessColor() == ChessColor.WHITE) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX() - 1, getChessboardPoint().getY()));
                }
            }
            if (getChessboardPoint().offset(0, 1) != null) {
                if (chessComponents[getChessboardPoint().getX()][getChessboardPoint().getY() + 1] instanceof EmptySlotComponent) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX(), getChessboardPoint().getY() + 1));
                } else if (chessComponents[getChessboardPoint().getX()][getChessboardPoint().getY() + 1].getChessColor() == ChessColor.WHITE) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX(), getChessboardPoint().getY() + 1));
                }
            }
            if (getChessboardPoint().offset(0, -1) != null) {
                if (chessComponents[getChessboardPoint().getX()][getChessboardPoint().getY() - 1] instanceof EmptySlotComponent) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX(), getChessboardPoint().getY() - 1));
                } else if (chessComponents[getChessboardPoint().getX()][getChessboardPoint().getY() - 1].getChessColor() == ChessColor.WHITE) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX(), getChessboardPoint().getY() - 1));
                }
            }
        }else if (getChessColor() == ChessColor.WHITE) {
            if (getChessboardPoint().offset(1, 1) != null) {
                if (chessComponents[getChessboardPoint().getX() + 1][getChessboardPoint().getY() + 1] instanceof EmptySlotComponent) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX() + 1, getChessboardPoint().getY() + 1));
                } else if (chessComponents[getChessboardPoint().getX() + 1][getChessboardPoint().getY() + 1].getChessColor() == ChessColor.BLACK) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX() + 1, getChessboardPoint().getY() + 1));
                }
            }
            if (getChessboardPoint().offset(1, -1) != null) {
                if (chessComponents[getChessboardPoint().getX() + 1][getChessboardPoint().getY() - 1] instanceof EmptySlotComponent) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX() + 1, getChessboardPoint().getY() - 1));
                } else if (chessComponents[getChessboardPoint().getX() + 1][getChessboardPoint().getY() - 1].getChessColor() == ChessColor.BLACK) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX() + 1, getChessboardPoint().getY() - 1));
                }
            }
            if (getChessboardPoint().offset(-1, 1) != null) {
                if (chessComponents[getChessboardPoint().getX() - 1][getChessboardPoint().getY() + 1] instanceof EmptySlotComponent) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX() - 1, getChessboardPoint().getY() + 1));
                } else if (chessComponents[getChessboardPoint().getX() - 1][getChessboardPoint().getY() + 1].getChessColor() == ChessColor.BLACK) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX() - 1, getChessboardPoint().getY() + 1));
                }
            }
            if (getChessboardPoint().offset(-1, -1) != null) {
                if (chessComponents[getChessboardPoint().getX() - 1][getChessboardPoint().getY() - 1] instanceof EmptySlotComponent) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX() - 1, getChessboardPoint().getY() - 1));
                } else if (chessComponents[getChessboardPoint().getX() - 1][getChessboardPoint().getY() - 1].getChessColor() == ChessColor.BLACK) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX() - 1, getChessboardPoint().getY() - 1));
                }
            }
            if (getChessboardPoint().offset(1, 0) != null) {
                if (chessComponents[getChessboardPoint().getX() + 1][getChessboardPoint().getY()] instanceof EmptySlotComponent) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX() + 1, getChessboardPoint().getY()));
                } else if (chessComponents[getChessboardPoint().getX() + 1][getChessboardPoint().getY()].getChessColor() == ChessColor.BLACK) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX() + 1, getChessboardPoint().getY()));
                }
            }
            if (getChessboardPoint().offset(-1, 0) != null) {
                if (chessComponents[getChessboardPoint().getX() - 1][getChessboardPoint().getY()] instanceof EmptySlotComponent) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX() - 1, getChessboardPoint().getY()));
                } else if (chessComponents[getChessboardPoint().getX() - 1][getChessboardPoint().getY()].getChessColor() == ChessColor.BLACK) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX() - 1, getChessboardPoint().getY()));
                }
            }
            if (getChessboardPoint().offset(0, 1) != null) {
                if (chessComponents[getChessboardPoint().getX()][getChessboardPoint().getY() + 1] instanceof EmptySlotComponent) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX(), getChessboardPoint().getY() + 1));
                } else if (chessComponents[getChessboardPoint().getX()][getChessboardPoint().getY() + 1].getChessColor() == ChessColor.BLACK) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX(), getChessboardPoint().getY() + 1));
                }
            }
            if (getChessboardPoint().offset(0, -1) != null) {
                if (chessComponents[getChessboardPoint().getX()][getChessboardPoint().getY() - 1] instanceof EmptySlotComponent) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX(), getChessboardPoint().getY() - 1));
                } else if (chessComponents[getChessboardPoint().getX()][getChessboardPoint().getY() - 1].getChessColor() == ChessColor.BLACK) {
                    canMoveTo.add(new ChessboardPoint(getChessboardPoint().getX(), getChessboardPoint().getY() - 1));
                }
            }
        }
        return canMoveTo;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(kingImage, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth(), getHeight());
        }
    }
}
