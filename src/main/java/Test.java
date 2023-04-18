import org.abstractica.javacsg.Geometry3D;
import org.abstractica.javacsg.JavaCSG;
import org.abstractica.javacsg.JavaCSGFactory;

import java.util.ArrayList;

public class Test
{
	public static void main(String[] args) {
		JavaCSG csg = JavaCSGFactory.createDefault();
		Geometry3D center = csg.cylinder3D(17, 200, 162, true);

// create cylinders around the center cylinder
		int numCylinders = 100;
		double angle = 0; // starting angle
		double radius = 15; // starting radius
		for (int i = 0; i < numCylinders; i++) {
			double x = Math.sin(angle) * radius;
			double y = Math.cos(angle) * radius;
			Geometry3D cyl = csg.cylinder3D(17, 100, 62, true);
			cyl = csg.rotate3D(csg.degrees(y), csg.degrees(-x), csg.degrees(0) ).transform(cyl);
			cyl=csg.translate3D(x, y+3, 75).transform(cyl);
			center = csg.difference3D(center, cyl);
			angle += Math.toRadians(360.0 / numCylinders);
		}

		for (int i = 0; i < numCylinders; i++) {
			radius=10;
			double x = Math.sin(angle) * radius;
			double y = Math.cos(angle) * radius;
			Geometry3D cyl = csg.cylinder3D(17, 100, 62, true);
			cyl = csg.rotate3D(csg.degrees(-y), csg.degrees(x), csg.degrees(0) ).transform(cyl);
			cyl=csg.translate3D(x, y-2, -93).transform(cyl);
			center = csg.difference3D(center, cyl);
			angle += Math.toRadians(360.0 / numCylinders);
		}


		var box= csg.box3D(60,5,50,false);
		box=csg.translate3D(0,0,-30).transform(box);
		var box2= csg.box3D(80,5,50,false);
		box2=csg.rotate3D(csg.degrees(0), csg.degrees(45), csg.degrees(0)).transform(box2);
		box2=csg.union3D(box2,box);
		var cyl3=csg.cylinder3D(220,15,82,true);
		cyl3=csg.rotate3D(csg.degrees(90), csg.degrees(90), csg.degrees(0)).transform(cyl3);
		cyl3=csg.translate3D(114, 0, 100).transform(cyl3);
		box2=csg.difference3D(box2,cyl3);
		var cyl4=csg.cylinder3D(43,5,82,true);
		cyl4=csg.rotate3D(csg.degrees(90), csg.degrees(90), csg.degrees(0)).transform(cyl4);
		cyl4=csg.translate3D(46, 0, -12).transform(cyl4);

		box2=csg.union3D(box2,cyl4);
		box2=csg.translate3D(0,0,-60).transform(box2);
		var box5= csg.box3D(150,8,50,false);
		box5=csg.translate3D(0,0,-126).transform(box5);
		box2=csg.difference3D(box2,box5);
		var box6= csg.box3D(134.5,5,10,false);
		box6=csg.translate3D(0,0,-84).transform(box6);
		var cyl5=csg.cylinder3D(650,15,82,true);
		cyl5=csg.rotate3D(csg.degrees(90), csg.degrees(90), csg.degrees(0)).transform(cyl5);
		cyl5=csg.translate3D(0, 0, 243).transform(cyl5);
		box6=csg.intersection3D(box6,cyl5);
		box6=csg.translate3D(0,0,-1.5).transform(box6);
		box2=csg.union3D(box2,box6);

		var box7=csg.rotate3D(csg.degrees(0),csg.degrees(0),csg.degrees(180)).transform(box2);
		box2=csg.union3D(box2,box7);
		box2=csg.translate3D(0, 0, 20).transform(box2);

		var engine=csg.box3D(14,10,30,true);
		engine=csg.translate3D(30,5,-40).transform(engine);
		var engineCut=csg.box3D(20,10,30,true);
		engineCut=csg.translate3D(30,-14,-30).transform(engineCut);
		engineCut=csg.rotate3D(csg.degrees(55),csg.degrees(0), csg.degrees(0)).transform(engineCut);
		engine=csg.difference3D(engine,engineCut);

		var engineCutout=csg.box3D(5,5,20,true);
		engineCutout=csg.translate3D(33,6.5,-30).transform(engineCutout);
		engine=csg.difference3D(engine,engineCutout);
		engineCutout=csg.translate3D(-6,0,0).transform(engineCutout);
		engine=csg.difference3D(engine,engineCutout);


		box2=csg.union3D(box2,engine);
		engine=csg.translate3D(-60,0,0).transform(engine);
		box2=csg.union3D(box2,engine);
		box2=csg.translate3D(0,5,10).transform(box2);
		box2=csg.union3D(box2,center);


		var landingGear = csg.cylinder3D(2,15,62,true);
		landingGear=csg.rotate3D(csg.degrees(90), csg.degrees(90), csg.degrees(0)).transform(landingGear);
		landingGear=csg.translate3D(0, 15, 0).transform(landingGear);
		var landingGear2 = csg.cylinder3D(1.5,6.5,62,true);
		landingGear2=csg.rotate3D(csg.degrees(90), csg.degrees(90), csg.degrees(-20)).transform(landingGear2);
		landingGear2=csg.translate3D(-1, 10, 0).transform(landingGear2);
		landingGear=csg.union3D(landingGear,landingGear2);
		landingGear2=csg.rotate3D(csg.degrees(0), csg.degrees(180), csg.degrees(0)).transform(landingGear2);
		landingGear=csg.union3D(landingGear,landingGear2);
		var wheel=csg.cylinder3D(4,2.3,82,true);
		wheel=csg.rotate3D(csg.degrees(0), csg.degrees(90), csg.degrees(0)).transform(wheel);
		wheel=csg.translate3D(2, 22, 0).transform(wheel);
		landingGear=csg.union3D(landingGear,wheel);
		wheel=csg.translate3D(-4, 0, 0).transform(wheel);
		landingGear=csg.union3D(landingGear,wheel);
		box2=csg.union3D(box2,landingGear);


		var landingGear3 = csg.cylinder3D(2,16,62,true);
		landingGear3=csg.rotate3D(csg.degrees(90), csg.degrees(90), csg.degrees(0)).transform(landingGear3);
		landingGear3=csg.translate3D(20, 15, -30).transform(landingGear3);
		var landingGear4 = csg.cylinder3D(1.5,14.5,62,true);
		landingGear4=csg.rotate3D(csg.degrees(90), csg.degrees(90), csg.degrees(-40)).transform(landingGear4);
		landingGear4=csg.translate3D(15, 10, -30).transform(landingGear4);
		landingGear3=csg.union3D(landingGear3,landingGear4);
		var wheel2=csg.cylinder3D(4,2.3,82,true);
		wheel2=csg.rotate3D(csg.degrees(0), csg.degrees(90), csg.degrees(0)).transform(wheel2);
		wheel2=csg.translate3D(22, 22, -30).transform(wheel2);
		landingGear3=csg.union3D(landingGear3,wheel2);
		wheel2=csg.translate3D(-4, 0, 0).transform(wheel2);
		landingGear3=csg.union3D(landingGear3,wheel2);

		box2=csg.union3D(box2,landingGear3);
		landingGear3=csg.rotate3D(csg.degrees(0), csg.degrees(180), csg.degrees(0)).transform(landingGear3);
		landingGear3=csg.translate3D(0, 0, -60).transform(landingGear3);
		box2=csg.union3D(box2,landingGear3);



		
		var rudder=csg.box3D(45,3,30,false);
		rudder=csg.rotate3D(csg.degrees(90), csg.degrees(90), csg.degrees(0)).transform(rudder);
		rudder=csg.translate3D(0, 0, -60).transform(rudder);
		var rudderCut=csg.cylinder3D(130,10,82,true);
		rudderCut=csg.rotate3D(csg.degrees(0), csg.degrees(90), csg.degrees(0)).transform(rudderCut);
		rudderCut=csg.translate3D(0, -72, -24).transform(rudderCut);
		rudder=csg.difference3D(rudder,rudderCut);
		box2=csg.union3D(box2,rudder);


		box2=csg.rotate3D(csg.degrees(90), csg.degrees(180), csg.degrees(0)).transform(box2);
// display result
		csg.view(box2);

	}
}
