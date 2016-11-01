//package commandreference;
//
//import gui.FrontTurtle;
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.util.Duration;
//
//public class AnimationManager {
//
//	private Timeline myAnimation;
//	private KeyFrame myFrame;
//    private int millisecondDelay = 10000;
//    
//	public AnimationManager(Animatable a, FrontTurtle turtle){
//		myAnimation = new Timeline();
//		myFrame = new KeyFrame(Duration.millis(millisecondDelay), e -> a.setStepMethod(turtle));
//		Timeline animation;
//		animation = new Timeline();
//		animation.setCycleCount(Timeline.INDEFINITE);
//		animation.getKeyFrames().add(myFrame);
//	}
//	
//	public void startAnimation(){
//		myAnimation.play();
//	}
//	
//	public void stopAnimation(){
//		myAnimation.stop();
//	}
//	
//	public void setStepMethod(Animatable a, FrontTurtle turtle){
//		myFrame = new KeyFrame(Duration.millis(millisecondDelay), e -> a.setStepMethod(turtle));
//	}
//}
