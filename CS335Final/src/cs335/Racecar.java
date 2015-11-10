package cs335;

public class Racecar
{
	int carid = 0;
	
	float p = 0.0f;
	float size = 0.0f;
	
	public float carpx = 0.0f;
	public float carpy = 0.0f;
	public final float carpz = 20.0f;

	public float carspeed = 0.0f;
	public float carangle = 0.0f;

	float maxspeed = 250.0f;
	float minspeed = -50.0f;

	float caracc = 0.0f;
	
	float ACCFWN = 0.20f;      // forward, holding W, not turning
	float ACCFWT = 0.14f;     // forward, holding W, turning
	float ACCFCN = -0.04f;   // forward, coasting, not turning
	float ACCFCT = -0.06f;  // forward, coasting, turning
	float ACCFSN = -0.30f;    // forward, holding S, not turning
	float ACCFST = -0.4f;   // forward, holding S, turning

	float ACCBWN = -ACCFSN;      // backward, holding W, not turning
	float ACCBWT = -ACCFST;     // backward, holding W, turning
	float ACCBCN = -ACCFCN;   // backward, coasting, not turning
	float ACCBCT = -ACCFCT;  // backward, coasting, turning
	float ACCBSN = -ACCFWN;    // backward, holding S, not turning
	float ACCBST = -ACCFWT;   // backward, holding S, turning
	
	boolean in_curve = false;
	int curve_number = 1;
	int laps = 0;
	boolean lap_completed = false;

	public Racecar(int id, Racetrack track)
	{
		p = track.p;
		size = track.size;
		carid = id;
		if( carid == 0 )
		{
			carpx = -size * (2600.0f/p - 0.5f);
			carpy = size * (1193.0f/p - 0.5f);
		}
		else if( carid == 1 ) 
		{
			carpx = -size * (2300.0f/p - 0.5f);
			carpy = size * (1193.0f/p - 0.5f);
		}
		else if( carid == 2 )
		{
			carpx = -size * (2400.0f/p - 0.5f);
			carpy = size * (1141.5f/p - 0.5f);
		}
		else if( carid == 3 )
		{
			carpx = -size * (2500.0f/p - 0.5f);
			carpy = size * (1090.0f/p - 0.5f);
		}
		carspeed = 0.0f;
		carangle = 0.0f;
		caracc = 0.0f;
		in_curve = false;
		curve_number = 1;
		laps = 0;
		lap_completed = false;
	}
	
	void updateacceleration(boolean forward, int key1, boolean turning)
	{
		if( forward )
		{
			if( key1 == 1 )
			{
				if( turning )
				{
					if( carspeed < 50.0f )
					{
						caracc = 2.8f * ACCFWT;
					}
					else if( carspeed < 100.0f )
					{
						caracc = 2.0f * ACCFWT;
					}
					else if( carspeed < 150.0f )
					{
						caracc = 1.4f * ACCFWT;
					}
					else
					{
						caracc = ACCFWT;
					}
				}
				else
				{
					if( carspeed < 50.0f )
					{
						caracc = 2.8f * ACCFWN;
					}
					else if( carspeed < 100.0f )
					{
						caracc = 2.0f * ACCFWN;
					}
					else if( carspeed < 150.0f )
					{
						caracc = 1.4f * ACCFWN;
					}
					else
					{
						caracc = ACCFWN;
					}
				}
			}
			else if( key1 == 0 )
			{
				if( turning )
				{
					if( carspeed < 50.0f )
					{
						caracc = ACCFCT;
					}
					else if( carspeed < 100.0f )
					{
						caracc = 1.4f * ACCFCT;
					}
					else if( carspeed < 150.0f )
					{
						caracc = 2.0f * ACCFCT;
					}
					else
					{
						caracc = 2.8f * ACCFCT;
					}
				}
				else
				{
					if( carspeed < 50.0f )
					{
						caracc = ACCFCN;
					}
					else if( carspeed < 100.0f )
					{
						caracc = 1.4f * ACCFCN;
					}
					else if( carspeed < 150.0f )
					{
						caracc = 2.0f * ACCFCN;
					}
					else
					{
						caracc = 2.8f * ACCFCN;
					}
				}
			}
			else if( key1 == -1 )
			{
				if( turning )
				{
					caracc = 2.8f * ACCFST;
					/*
					if( carspeed < 50.0f )
					{
						caracc = ACCFST;
					}
					else if( carspeed < 100.0f )
					{
						caracc = 1.4f * ACCFST;
					}
					else if( carspeed < 150.0f )
					{
						caracc = 2.0f * ACCFST;
					}
					else
					{
						caracc = 2.8f * ACCFST;
					}
					*/
				}
				else
				{
					caracc = 2.8f * ACCFSN;
					/*
					if( carspeed < 50.0f )
					{
						caracc = ACCFSN;
					}
					else if( carspeed < 100.0f )
					{
						caracc = 1.4f * ACCFSN;
					}
					else if( carspeed < 150.0f )
					{
						caracc = 2.0f * ACCFSN;
					}
					else
					{
						caracc = 2.8f * ACCFSN;
					}
					*/
				}
			}
		}
		else
		{
			if( key1 == 1 )
			{
				if( turning )
				{
					caracc = 2.8f * ACCBWT;
				}
				else
				{
					caracc = 2.8f * ACCBWN;
				}
			}
			else if( key1 == 0 )
			{
				if( turning )
				{
					caracc = ACCBCT;
				}
				else
				{
					caracc = ACCBCN;
				}
			}
			else if( key1 == -1 )
			{
				if( turning )
				{
					caracc = 2.8f * ACCBST;
				}
				else
				{
					caracc = 2.8f * ACCBSN;
				}
			}
		}
	}
	
	void userposition(float t, boolean keyw, boolean keys, boolean keya, boolean keyd, Racetrack track)
	{
		boolean forward = true;
		if( carspeed < 0 )
		{
			forward = false;
		}
	
		int movement = 0;
		if( keyw )
		{
			if( !keys )
			{
				movement = 1;
			}
		}
		else if( keys )
		{
			if( !keyw )
			{
				movement = -1;
			}
		}
	
		boolean turning = false;
		int turndirection = 0;
		if( keya )
		{
			if( !keyd )
			{
				turning = true;
				turndirection = 1;
			}
		}
		else if( keyd )
		{
			if( !keya )
			{
				turning = true;
				turndirection = -1;
			}
		}
	
		updateacceleration(forward, movement, turning);
		
		if( (carspeed < 0) && (caracc + carspeed > 0) )
		{
			carspeed = 0;
		}
		else if( (carspeed > 0) && (caracc + carspeed < 0) )
		{
			carspeed = 0;
		}
		else if( carspeed == 0 && movement == 0 )
		{
			carspeed = 0;
		}
		else
		{
			carspeed += caracc;
		}
					
		if( carspeed > maxspeed )
		{
			carspeed = maxspeed;
		}
		else if( carspeed < minspeed )
		{
			carspeed = minspeed;
		}
		
		System.out.printf("lap = %d, curve = %d, x = %4.0f, y = %4.0f, speed = %3.3f\n", laps+1, curve_number, carpx, carpy, carspeed);
		
		if( turning )
		{
			carangle += (float)turndirection * 1.15f / (0.05f / t);
		}
		
		carpx += carspeed * (float)Math.cos(carangle * (float)Math.PI / 180.0f) * t;
		carpy += carspeed * (float)Math.sin(carangle * (float)Math.PI / 180.0f) * t;
		
		
		float r = track.curve_radius(curve_number, 2);
		
		if( curve_number == 1 )
		{
			if( in_curve )
			{
				if( carpy > (size * ((r * (float)Math.sin((0.0f) * (float)Math.PI / 180.0f) + track.c1y) / p - 0.5f ) ) )
				{
					curve_number++;
					in_curve = false;
				}
			}
			else
			{
				// add start-finish line checks
				increase_lap();
				
				if( carpx > (-size * ((r * -(float)Math.cos((-90.0f) * (float)Math.PI / 180.0f) + track.c1x) / p - 0.5f ) ) )
				{
					in_curve = true;
				}
			}
		}
		else if( curve_number == 2 )
		{
			if( in_curve )
			{
				if( carpy < (size * ((r * (float)Math.sin((180.0f) * (float)Math.PI / 180.0f) + track.c2y) / p - 0.5f ) ) )
				{
					curve_number++;
					in_curve = false;
				}
			}
			else
			{
				if( carpy > (size * ((r * (float)Math.sin((0.0f) * (float)Math.PI / 180.0f) + track.c2y) / p - 0.5f ) ) )
				{
					in_curve = true;				
				}
			}
		}
		else if( curve_number == 3 )
		{
			if( in_curve )
			{
				if( carpy > (size * ((r * (float)Math.sin((-175.0f) * (float)Math.PI / 180.0f) + track.c3y) / p - 0.5f ) ) && carpx < (-size * (track.c3x / p - 0.5f) ) )
				{
					curve_number++;
					in_curve = false;
				}
			}
			else
			{
				if( carpy < (size * ((r * (float)Math.sin((0.0f) * (float)Math.PI / 180.0f) + track.c3y) / p - 0.5f ) ) )
				{
					in_curve = true;
				}
			}
		}
		else if( curve_number == 4 )
		{
			if( in_curve )
			{
				if( carpy < (size * ((r * (float)Math.sin((180.0f) * (float)Math.PI / 180.0f) + track.c4y) / p - 0.5f ) ) )
				{
					curve_number++;
					in_curve = false;
				}
			}
			else
			{
				if( carpy > (size * ((r * (float)Math.sin((5.0f) * (float)Math.PI / 180.0f) + track.c4y) / p - 0.5f ) ) )
				{
					in_curve = true;
				}
			}
		}
		else if( curve_number == 5 )
		{
			if( in_curve )
			{
				if( carpy > (size * ((r * (float)Math.sin((-180.0f) * (float)Math.PI / 180.0f) + track.c5y) / p - 0.5f ) ) )
				{
					curve_number++;
					in_curve = false;
				}
			}
			else
			{
				if( carpy < (size * ((r * (float)Math.sin((0.0f) * (float)Math.PI / 180.0f) + track.c5y) / p - 0.5f ) ) )
				{
					in_curve = true;
				}
			}
		}
		else if( curve_number == 6 )
		{
			if( in_curve )
			{
				if( carpy < (size * ((r * (float)Math.sin((150.0f) * (float)Math.PI / 180.0f) + track.c6y) / p - 0.5f ) ) && carpx < (-size * (track.c6x / p - 0.5f) ) )
				{
					curve_number++;
					in_curve = false;
				}
			}
			else
			{
				if( carpy > (size * ((r * (float)Math.sin((0.0f) * (float)Math.PI / 180.0f) + track.c6y) / p - 0.5f ) ) )
				{
					in_curve = true;
				}
			}
		}
		else if( curve_number == 7 )
		{
			if( in_curve )
			{
				if( carpx > (-size * ((r * -(float)Math.cos((270.0f) * (float)Math.PI / 180.0f) + track.c7x) / p - 0.5f ) ) )
				{
					curve_number = 1;
					in_curve = false;
					lap_completed = true;
				}
			}
			else
			{			
				if( carpy < (size * ((r * (float)Math.sin((150.0f) * (float)Math.PI / 180.0f) + track.c7y) / p - 0.5f ) ) )
				{
					in_curve = true;
				}
			}
		}
	}
	
	void aiposition(float t, Racetrack track)
	{		
		// System.out.printf("x = %4.0f, y = %4.0f, speed = %3.3f\n", carpx, carpy, carspeed);
		
		if( in_curve )
		{
			float r = track.curve_radius(curve_number, carid);
			float a = track.curve_angle(curve_number);
			
			// a = 180 --> slow (2x)
			// a = 90 --> fast (2x)
			// 90 a = 90 speed (180.0f - a)
			// r = 357 --> fast (1x)
			// r = 139 --> slow (1x)
			// 100 r = 10 speed  (r / 10.0f)
			
			float curvespeed = (180.0f - a) + (r / 10.0f) + 100.0f;
			
			if( carspeed > curvespeed )
			{
				carspeed -= 0.8f;
				if( carspeed < curvespeed )
				{
					carspeed = curvespeed;
				}
			}
			else if( carspeed < curvespeed )
			{
				if( carspeed < 50.0f )
				{
					carspeed += 2.74f * ACCFWT;
				}
				else if( carspeed < 100.0f )
				{
					carspeed += 1.94f * ACCFWT;
				}
				else if( carspeed < 150.0f )
				{
					carspeed += 1.34f * ACCFWT;
				}
				else
				{
					carspeed += 0.94f * ACCFWT;
					if( carspeed > maxspeed )
					{
						carspeed = maxspeed;
					}
				}
				if( carspeed > curvespeed )
				{
					carspeed = curvespeed;
				}
			}
			
			float dist = carspeed * t;
			
			float angle = 360.0f * dist / (2.0f * (float)Math.PI * r * size / p);
			
			if( curve_number == 3 || curve_number == 5 )
			{
				carangle -= angle;
				if( carangle < (track.car_end_angle(curve_number)) )
				{
					carangle = (track.car_end_angle(curve_number));
					in_curve = false;
				}
			}
			else
			{
				carangle += angle;
				if( carangle > (track.car_end_angle(curve_number)) )
				{
					carangle = (track.car_end_angle(curve_number));
					in_curve = false;
				}
			}
			
			if( curve_number == 1 )
			{
				carpx = -size * ((r * -(float)Math.cos((carangle - 90.0f) * (float)Math.PI / 180.0f) + track.c1x) / p - 0.5f );
				carpy = size * ((r * (float)Math.sin((carangle - 90.0f) * (float)Math.PI / 180.0f) + track.c1y) / p - 0.5f );
			}
			else if( curve_number == 2 )
			{
				carpx = -size * ((r * -(float)Math.cos((carangle - 90.0f) * (float)Math.PI / 180.0f) + track.c2x) / p - 0.5f );
				carpy = size * ((r * (float)Math.sin((carangle - 90.0f) * (float)Math.PI / 180.0f) + track.c2y) / p - 0.5f );
			}
			else if( curve_number == 3 )
			{
				carpx = -size * ((r * -(float)Math.cos((carangle + 90.0f) * (float)Math.PI / 180.0f) + track.c3x) / p - 0.5f );
				carpy = size * ((r * (float)Math.sin((carangle + 90.0f) * (float)Math.PI / 180.0f) + track.c3y) / p - 0.5f );
			}
			else if( curve_number == 4 )
			{
				carpx = -size * ((r * -(float)Math.cos((carangle - 90.0f) * (float)Math.PI / 180.0f) + track.c4x) / p - 0.5f );
				carpy = size * ((r * (float)Math.sin((carangle - 90.0f) * (float)Math.PI / 180.0f) + track.c4y) / p - 0.5f );
			}
			else if( curve_number == 5 )
			{
				carpx = -size * ((r * -(float)Math.cos((carangle + 90.0f) * (float)Math.PI / 180.0f) + track.c5x) / p - 0.5f );
				carpy = size * ((r * (float)Math.sin((carangle + 90.0f) * (float)Math.PI / 180.0f) + track.c5y) / p - 0.5f );
			}
			else if( curve_number == 6 )
			{
				carpx = -size * ((r * -(float)Math.cos((carangle - 90.0f) * (float)Math.PI / 180.0f) + track.c6x) / p - 0.5f );
				carpy = size * ((r * (float)Math.sin((carangle - 90.0f) * (float)Math.PI / 180.0f) + track.c6y) / p - 0.5f );
			}
			else if( curve_number == 7 )
			{
				carpx = -size * ((r * -(float)Math.cos((carangle - 90.0f) * (float)Math.PI / 180.0f) + track.c7x) / p - 0.5f );
				carpy = size * ((r * (float)Math.sin((carangle - 90.0f) * (float)Math.PI / 180.0f) + track.c7y) / p - 0.5f );
			}
			
			if( !in_curve )
			{
				curve_number++;
				if( curve_number == 8 )
				{
					curve_number = 1;
					lap_completed = true;
					carangle = 0;
				}
			}
		}
		else
		{
			// float dist = track.straight_distance(curve_number, carid);
			// float straightspeed = dist / 8.0f;
			
			boolean slowdown = false;
			
			if( curve_number == 1 )
			{
				if( (p * (-carpx / size + 0.5f) - 0.5 * track.curve_radius(1, carid) ) < track.c1x )
				{
					carspeed -= 0.5f;
					slowdown = true;
				}
			}
			else if( curve_number == 2 )
			{
				if( (p * (carpy / size + 0.5f) + track.curve_radius(2, carid) ) > track.c2y )
				{
					carspeed -= 0.5f;
					slowdown = true;
				}
			}
			else if( curve_number == 3 )
			{
				if( (p * (carpy / size + 0.5f) - track.curve_radius(3, carid) ) < track.c3y )
				{
					carspeed -= 0.5f;
					slowdown = true;
				}
			}
			else if( curve_number == 4 )
			{
				if( (p * (carpy / size + 0.5f) + track.curve_radius(4, carid) ) > track.c4y )
				{
					carspeed -= 0.5f;
					slowdown = true;
				}
			}
			else if( curve_number == 5 )
			{
				if( (p * (carpy / size + 0.5f) - track.curve_radius(5, carid) ) < track.c5y )
				{
					carspeed -= 0.5f;
					slowdown = true;
				}
			}
			else if( curve_number == 6 )
			{
				if( (p * (carpy / size + 0.5f) + track.curve_radius(6, carid) ) > track.c6y )
				{
					carspeed -= 0.5f;
					slowdown = true;
				}
			}
			else if( curve_number == 7 )
			{
				if( (p * (carpy / size + 0.5f) - 0.75 * track.curve_radius(7, carid) ) < track.c7y )
				{
					carspeed -= 0.5f;
					slowdown = true;
				}
			}
			
			if( !slowdown )
			{
				if( carspeed < 50.0f )
				{
					carspeed += 2.74f * ACCFWN;
				}
				else if( carspeed < 100.0f )
				{
					carspeed += 1.94f * ACCFWN;
				}
				else if( carspeed < 150.0f )
				{
					carspeed += 1.34f * ACCFWN;
				}
				else
				{
					carspeed += 0.94f * ACCFWN;
					if( carspeed > maxspeed )
					{
						carspeed = maxspeed;
					}
				}
			}
			
			carpx += carspeed * (float)Math.cos((carangle) * (float)Math.PI / 180.0f) * t;
			carpy += carspeed * (float)Math.sin((carangle) * (float)Math.PI / 180.0f) * t;
			
			
			float r = track.curve_radius(curve_number, carid);
			
			if( curve_number == 1 )
			{
				increase_lap();
				if( carpx > (-size * ((r * -(float)Math.cos((-90.0f) * (float)Math.PI / 180.0f) + track.c1x) / p - 0.5f ) ) )
				{		
					carpx = -size * ((r * -(float)Math.cos((-90.0f) * (float)Math.PI / 180.0f) + track.c1x) / p - 0.5f );
					carpy = size * ((r * (float)Math.sin((-90.0f) * (float)Math.PI / 180.0f) + track.c1y) / p - 0.5f );
					carangle = track.car_start_angle(1);
					in_curve = true;
				}
			}
			else if( curve_number == 2 )
			{
				if( carpy > (size * ((r * (float)Math.sin((0.0f) * (float)Math.PI / 180.0f) + track.c2y) / p - 0.5f ) ) )
				{
					carpx = -size * ((r * -(float)Math.cos((0.0f) * (float)Math.PI / 180.0f) + track.c2x) / p - 0.5f );
					carpy = size * ((r * (float)Math.sin((0.0f) * (float)Math.PI / 180.0f) + track.c2y) / p - 0.5f );
					carangle = track.car_start_angle(2);
					in_curve = true;
				}
			}
			else if( curve_number == 3 )
			{
				if( carpy < (size * ((r * (float)Math.sin((0.0f) * (float)Math.PI / 180.0f) + track.c3y) / p - 0.5f ) ) )
				{
					carpx = -size * ((r * -(float)Math.cos((0.0f) * (float)Math.PI / 180.0f) + track.c3x) / p - 0.5f );
					carpy = size * ((r * (float)Math.sin((0.0f) * (float)Math.PI / 180.0f) + track.c3y) / p - 0.5f );
					carangle = track.car_start_angle(3);
					in_curve = true;
				}
			}
			else if( curve_number == 4 )
			{
				if( carpy > (size * ((r * (float)Math.sin((5.0f) * (float)Math.PI / 180.0f) + track.c4y) / p - 0.5f ) ) )
				{
					carpx = -size * ((r * -(float)Math.cos((5.0f) * (float)Math.PI / 180.0f) + track.c4x) / p - 0.5f );
					carpy = size * ((r * (float)Math.sin((5.0f) * (float)Math.PI / 180.0f) + track.c4y) / p - 0.5f );
					carangle = track.car_start_angle(4);
					in_curve = true;
				}
			}
			else if( curve_number == 5 )
			{
				if( carpy < (size * ((r * (float)Math.sin((0.0f) * (float)Math.PI / 180.0f) + track.c5y) / p - 0.5f ) ) )
				{
					carpx = -size * ((r * -(float)Math.cos((0.0f) * (float)Math.PI / 180.0f) + track.c5x) / p - 0.5f );
					carpy = size * ((r * (float)Math.sin((0.0f) * (float)Math.PI / 180.0f) + track.c5y) / p - 0.5f );
					carangle = track.car_start_angle(5);
					in_curve = true;
				}
			}
			else if( curve_number == 6 )
			{
				if( carpy > (size * ((r * (float)Math.sin((0.0f) * (float)Math.PI / 180.0f) + track.c6y) / p - 0.5f ) ) )
				{
					carpx = -size * ((r * -(float)Math.cos((0.0f) * (float)Math.PI / 180.0f) + track.c6x) / p - 0.5f );
					carpy = size * ((r * (float)Math.sin((0.0f) * (float)Math.PI / 180.0f) + track.c6y) / p - 0.5f );
					carangle = track.car_start_angle(6);
					in_curve = true;
				}
			}
			else if( curve_number == 7 )
			{
				if( carpy < (size * ((r * (float)Math.sin((150.0f) * (float)Math.PI / 180.0f) + track.c7y) / p - 0.5f ) ) )
				{
					carpx = -size * ((r * -(float)Math.cos((150.0f) * (float)Math.PI / 180.0f) + track.c7x) / p - 0.5f );
					carpy = size * ((r * (float)Math.sin((150.0f) * (float)Math.PI / 180.0f) + track.c7y) / p - 0.5f );
					carangle = track.car_start_angle(7);
					in_curve = true;
				}
			}
		}
	}
	
	public void updateposition(float t, boolean keyw, boolean keys, boolean keya, boolean keyd, Racetrack track)
	{
		if( carid == 0 )  // user-controlled car
		{
			userposition(t, keyw, keys, keya, keyd, track);
		}
		else             // AI-controlled car
		{
			aiposition(t, track);
		}
	}
	
	boolean collision(float r, Racecar ai)
	{
		boolean c = false;
		float dist = (float)Math.sqrt(Math.pow((carpx-ai.carpx),2) + Math.pow((carpy-ai.carpy),2));
		if( dist < (2*r) )
		{
			c = true;
			float s = (2*r) / dist;
			carpx = s * (carpx - ai.carpx) + ai.carpx;
			carpy = s * (carpy - ai.carpy) + ai.carpy;
			ai.carspeed -= 1.4f;
			if( ai.carspeed < 0.0f )
			{
				ai.carspeed = 0.0f;
			}
		}
		return c;
	}
	
	public void checkcollisions(float r, Racetrack track, Racecar car1, Racecar car2, Racecar car3)
	{
		boolean c = false;
		// collisions with AI cars
		if( collision(r, car1) || collision(r, car2) || collision(r, car3) )
		{
			c = true;
		}
		
		// collisions with walls
		if( in_curve )
		{			
			float lr, rr, cx, cy;
			float lxc1, lyc1, rxc1, ryc1;
			float lxc2, lyc2, rxc2, ryc2;
			
			if( curve_number == 1 )
			{
				lr = size * track.left_r1 / p;
				rr = size * track.right_r1 / p;
				cx = -size * (track.c1x / p - 0.5f);
				cy = size * (track.c1y / p - 0.5f);
				
				lxc1 = cx + (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpy - cy, 2) );
				rxc1 = cx + (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpy - cy, 2) );
				
				lxc2 = cx - (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpy - cy, 2) );
				rxc2 = cx - (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpy - cy, 2) );
				
				if( carpx + r > rxc1 )
				{
					c = true;
					carpx = rxc1 - r;
				}
				else if( carpx - r < lxc1 )
				{
					c = true;
					carpx = lxc1 + r;
				}
				
				lyc1 = cy + (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpx - cx, 2) );
				ryc1 = cy + (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpx - cx, 2) );
				lyc2 = cy - (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpx - cx, 2) );
				ryc2 = cy - (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpx - cx, 2) );
				
				if( carpy + r > lyc2 )
				{
					c = true;
					carpy = lyc2 - r;
				}
				else if( carpy - r < ryc2 )
				{
					c = true;
					carpy = ryc2 + r;
				}	
			}
			else if( curve_number == 2 )
			{
				lr = size * track.left_r2 / p;
				rr = size * track.right_r2 / p;
				cx = -size * (track.c2x / p - 0.5f);
				cy = size * (track.c2y / p - 0.5f);
				
				lxc1 = cx + (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpy - cy, 2) );
				rxc1 = cx + (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpy - cy, 2) );
				
				lxc2 = cx - (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpy - cy, 2) );
				rxc2 = cx - (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpy - cy, 2) );
				
				if( carpx - cx >= 0 )
				{
					if( carpx + r > rxc1 )
					{
						c = true;
						carpx = rxc1 - r;
					}
					else if( carpx - r < lxc1 )
					{
						c = true;
						carpx = lxc1 + r;
					}
					
				}
				else
				{
					if( carpx + r > lxc2 )
					{
						c = true;
						carpx = lxc2 - r;
					}
					else if( carpx - r < rxc2 )
					{
						c = true;
						carpx = rxc2 + r;
					}
				}
				
				lyc1 = cy + (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpx - cx, 2) );
				ryc1 = cy + (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpx - cx, 2) );
				lyc2 = cy - (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpx - cx, 2) );
				ryc2 = cy - (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpx - cx, 2) );
				
				if( carpy + r > ryc1 )
				{
					c = true;
					carpy = ryc1 - r;
				}
				else if( carpy - r < lyc1 )
				{
					c = true;
					carpy = lyc1 + r;
				}	
			}
			else if( curve_number == 3 )
			{
				lr = size * track.left_r3 / p;
				rr = size * track.right_r3 / p;
				cx = -size * (track.c3x / p - 0.5f);
				cy = size * (track.c3y / p - 0.5f);
				
				lxc1 = cx + (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpy - cy, 2) );
				rxc1 = cx + (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpy - cy, 2) );
				
				lxc2 = cx - (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpy - cy, 2) );
				rxc2 = cx - (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpy - cy, 2) );
				
				if( carpx - cx >= 0 )
				{
					if( carpx + r > lxc1 )
					{
						c = true;
						carpx = lxc1 - r;
					}
					else if( carpx - r < rxc1 )
					{
						c = true;
						carpx = rxc1 + r;
					}
					
				}
				else
				{
					if( carpx + r > rxc2 )
					{
						c = true;
						carpx = rxc2 - r;
					}
					else if( carpx - r < lxc2 )
					{
						c = true;
						carpx = lxc2 + r;
					}
				}
				
				lyc1 = cy + (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpx - cx, 2) );
				ryc1 = cy + (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpx - cx, 2) );
				lyc2 = cy - (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpx - cx, 2) );
				ryc2 = cy - (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpx - cx, 2) );
				
				if( carpy + r > ryc2 )
				{
					c = true;
					carpy = ryc2 - r;
				}
				else if( carpy - r < lyc2 )
				{
					c = true;
					carpy = lyc2 + r;
				}	
			}
			else if( curve_number == 4 )
			{
				lr = size * track.left_r4 / p;
				rr = size * track.right_r4 / p;
				cx = -size * (track.c4x / p - 0.5f);
				cy = size * (track.c4y / p - 0.5f);
				
				lxc1 = cx + (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpy - cy, 2) );
				rxc1 = cx + (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpy - cy, 2) );
				
				lxc2 = cx - (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpy - cy, 2) );
				rxc2 = cx - (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpy - cy, 2) );
				
				if( carpx - cx >= 0 )
				{
					if( carpx + r > rxc1 )
					{
						c = true;
						carpx = rxc1 - r;
					}
					else if( carpx - r < lxc1 )
					{
						c = true;
						carpx = lxc1 + r;
					}
					
				}
				else
				{
					if( carpx + r > lxc2 )
					{
						c = true;
						carpx = lxc2 - r;
					}
					else if( carpx - r < rxc2 )
					{
						c = true;
						carpx = rxc2 + r;
					}
				}
				
				lyc1 = cy + (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpx - cx, 2) );
				ryc1 = cy + (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpx - cx, 2) );
				lyc2 = cy - (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpx - cx, 2) );
				ryc2 = cy - (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpx - cx, 2) );
				
				if( carpy + r > ryc1 )
				{
					c = true;
					carpy = ryc1 - r;
				}
				else if( carpy - r < lyc1 )
				{
					c = true;
					carpy = lyc1 + r;
				}		
			}
			else if( curve_number == 5 )
			{
				lr = size * track.left_r5 / p;
				rr = size * track.right_r5 / p;
				cx = -size * (track.c5x / p - 0.5f);
				cy = size * (track.c5y / p - 0.5f);
				
				lxc1 = cx + (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpy - cy, 2) );
				rxc1 = cx + (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpy - cy, 2) );
				
				lxc2 = cx - (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpy - cy, 2) );
				rxc2 = cx - (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpy - cy, 2) );
				
				if( carpx - cx >= 0 )
				{
					if( carpx + r > lxc1 )
					{
						c = true;
						carpx = lxc1 - r;
					}
					else if( carpx - r < rxc1 )
					{
						c = true;
						carpx = rxc1 + r;
					}
					
				}
				else
				{
					if( carpx + r > rxc2 )
					{
						c = true;
						carpx = rxc2 - r;
					}
					else if( carpx - r < lxc2 )
					{
						c = true;
						carpx = lxc2 + r;
					}
				}
				
				lyc1 = cy + (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpx - cx, 2) );
				ryc1 = cy + (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpx - cx, 2) );
				lyc2 = cy - (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpx - cx, 2) );
				ryc2 = cy - (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpx - cx, 2) );
				
				if( carpy + r > ryc2 )
				{
					c = true;
					carpy = ryc2 - r;
				}
				else if( carpy - r < lyc2 )
				{
					c = true;
					carpy = lyc2 + r;
				}		
			}
			else if( curve_number == 6 )
			{
				lr = size * track.left_r6 / p;
				rr = size * track.right_r6 / p;
				cx = -size * (track.c6x / p - 0.5f);
				cy = size * (track.c6y / p - 0.5f);
				
				lxc1 = cx + (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpy - cy, 2) );
				rxc1 = cx + (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpy - cy, 2) );
				
				lxc2 = cx - (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpy - cy, 2) );
				rxc2 = cx - (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpy - cy, 2) );
				
				if( carpx - cx >= 0 )
				{
					if( carpx + r > rxc1 )
					{
						c = true;
						carpx = rxc1 - r;
					}
					else if( carpx - r < lxc1 )
					{
						c = true;
						carpx = lxc1 + r;
					}
					
				}
				else
				{
					if( carpx + r > lxc2 )
					{
						c = true;
						carpx = lxc2 - r;
					}
					else if( carpx - r < rxc2 )
					{
						c = true;
						carpx = rxc2 + r;
					}
				}
				
				lyc1 = cy + (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpx - cx, 2) );
				ryc1 = cy + (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpx - cx, 2) );
				lyc2 = cy - (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpx - cx, 2) );
				ryc2 = cy - (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpx - cx, 2) );
				
				if( carpy + r > ryc1 )
				{
					c = true;
					carpy = ryc1 - r;
				}
				else if( carpy - r < lyc1 )
				{
					c = true;
					carpy = lyc1 + r;
				}	
			}
			else if( curve_number == 7 )
			{
				lr = size * track.left_r7 / p;
				rr = size * track.right_r7 / p;
				cx = -size * (track.c7x / p - 0.5f);
				cy = size * (track.c7y / p - 0.5f);
				
				lxc1 = cx + (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpy - cy, 2) );
				rxc1 = cx + (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpy - cy, 2) );
				
				lxc2 = cx - (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpy - cy, 2) );
				rxc2 = cx - (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpy - cy, 2) );
				
				if( carpx + r > lxc2 )
				{
					c = true;
					carpx = lxc2 - r;
				}
				else if( carpx - r < rxc2 )
				{
					c = true;
					carpx = rxc2 + r;
				}
				
				lyc1 = cy + (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpx - cx, 2) );
				ryc1 = cy + (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpx - cx, 2) );
				lyc2 = cy - (float)Math.sqrt( Math.pow(lr, 2) - Math.pow(carpx - cx, 2) );
				ryc2 = cy - (float)Math.sqrt( Math.pow(rr, 2) - Math.pow(carpx - cx, 2) );
				
				if( carpy - cy >= 0 )
				{
					if( carpy + r > ryc1 )
					{
						c = true;
						carpy = ryc1 - r;
					}
					else if( carpy - r < lyc1 )
					{
						c = true;
						carpy = lyc1 + r;
					}
					
				}
				else
				{
					if( carpy + r > lyc2 )
					{
						c = true;
						carpy = lyc2 - r;
					}
					else if( carpy - r < ryc2 )
					{
						c = true;
						carpy = ryc2 + r;
					}
				}
			}
		}
		else
		{
			// lx1, ly1 = end of curve 7 (left)
			// lx2, ly2 = start of curve 1 (left)
			// rx1, ry1 = end of curve 7 (right)
			// rx2, ry2 = start of curve 1 (right)
			float lx1, lx2, ly1, ly2;
			float rx1, rx2, ry1, ry2;
			
			float lxc, lyc, rxc, ryc;  //  coordinates of collision points
			
			if( curve_number == 1 )
			{				
				lx1 = -size * ((track.left_r7 * -(float)Math.cos((-90.0f) * (float)Math.PI / 180.0f) + track.c7x) / p - 0.5f );
				ly1 = size * ((track.left_r7 * (float)Math.sin((-90.0f) * (float)Math.PI / 180.0f) + track.c7y) / p - 0.5f );

				rx1 = -size * ((track.right_r7 * -(float)Math.cos((-90.0f) * (float)Math.PI / 180.0f) + track.c7x) / p - 0.5f );
				ry1 = size * ((track.right_r7 * (float)Math.sin((-90.0f) * (float)Math.PI / 180.0f) + track.c7y) / p - 0.5f );

				lx2 = -size * ((track.left_r1 * -(float)Math.cos((-90.0f) * (float)Math.PI / 180.0f) + track.c1x) / p - 0.5f );
				ly2 = size * ((track.left_r1 * (float)Math.sin((-90.0f) * (float)Math.PI / 180.0f) + track.c1y) / p - 0.5f );

				rx2 = -size * ((track.right_r1 * -(float)Math.cos((-90.0f) * (float)Math.PI / 180.0f) + track.c1x) / p - 0.5f );
				ry2 = size * ((track.right_r1 * (float)Math.sin((-90.0f) * (float)Math.PI / 180.0f) + track.c1y) / p - 0.5f );
				
				lxc = ((lx2 - lx1) / (ly2 - ly1)) * (carpy - ly1) + lx1;
				lyc = ((ly2 - ly1) / (lx2 - lx1)) * (carpx - lx1) + ly1;
				
				rxc = ((rx2 - rx1) / (ry2 - ry1)) * (carpy - ry1) + rx1;
				ryc = ((ry2 - ry1) / (rx2 - rx1)) * (carpx - rx1) + ry1;
				
				if( carpy + r > lyc )
				{
					c = true;
					carpy = lyc - r;
				}
				else if( carpy - r < ryc )
				{
					c = true;
					carpy = ryc + r;
				}		
			}
			else if( curve_number == 2 )
			{
				lx1 = -size * ((track.left_r1 * -(float)Math.cos((0.0f) * (float)Math.PI / 180.0f) + track.c1x) / p - 0.5f );
				ly1 = size * ((track.left_r1 * (float)Math.sin((0.0f) * (float)Math.PI / 180.0f) + track.c1y) / p - 0.5f );

				rx1 = -size * ((track.right_r1 * -(float)Math.cos((0.0f) * (float)Math.PI / 180.0f) + track.c1x) / p - 0.5f );
				ry1 = size * ((track.right_r1 * (float)Math.sin((0.0f) * (float)Math.PI / 180.0f) + track.c1y) / p - 0.5f );

				lx2 = -size * ((track.left_r2 * -(float)Math.cos((0.0f) * (float)Math.PI / 180.0f) + track.c2x) / p - 0.5f );
				ly2 = size * ((track.left_r2 * (float)Math.sin((0.0f) * (float)Math.PI / 180.0f) + track.c2y) / p - 0.5f );

				rx2 = -size * ((track.right_r2 * -(float)Math.cos((0.0f) * (float)Math.PI / 180.0f) + track.c2x) / p - 0.5f );
				ry2 = size * ((track.right_r2 * (float)Math.sin((0.0f) * (float)Math.PI / 180.0f) + track.c2y) / p - 0.5f );
				
				lxc = ((lx2 - lx1) / (ly2 - ly1)) * (carpy - ly1) + lx1;
				lyc = ((ly2 - ly1) / (lx2 - lx1)) * (carpx - lx1) + ly1;
				
				rxc = ((rx2 - rx1) / (ry2 - ry1)) * (carpy - ry1) + rx1;
				ryc = ((ry2 - ry1) / (rx2 - rx1)) * (carpx - rx1) + ry1;
				
				if( carpx + r > rxc )
				{
					c = true;
					carpx = rxc - r;
				}
				else if( carpx - r < lxc )
				{
					c = true;
					carpx = lxc + r;
				}	
			}
			else if( curve_number == 3 )
			{
				lx1 = -size * ((track.left_r2 * -(float)Math.cos((180.0f) * (float)Math.PI / 180.0f) + track.c2x) / p - 0.5f );
				ly1 = size * ((track.left_r2 * (float)Math.sin((180.0f) * (float)Math.PI / 180.0f) + track.c2y) / p - 0.5f );

				rx1 = -size * ((track.right_r2 * -(float)Math.cos((180.0f) * (float)Math.PI / 180.0f) + track.c2x) / p - 0.5f );
				ry1 = size * ((track.right_r2 * (float)Math.sin((180.0f) * (float)Math.PI / 180.0f) + track.c2y) / p - 0.5f );

				lx2 = -size * ((track.left_r3 * -(float)Math.cos((0.0f) * (float)Math.PI / 180.0f) + track.c3x) / p - 0.5f );
				ly2 = size * ((track.left_r3 * (float)Math.sin((0.0f) * (float)Math.PI / 180.0f) + track.c3y) / p - 0.5f );

				rx2 = -size * ((track.right_r3 * -(float)Math.cos((0.0f) * (float)Math.PI / 180.0f) + track.c3x) / p - 0.5f );
				ry2 = size * ((track.right_r3 * (float)Math.sin((0.0f) * (float)Math.PI / 180.0f) + track.c3y) / p - 0.5f );
				
				lxc = ((lx2 - lx1) / (ly2 - ly1)) * (carpy - ly1) + lx1;
				lyc = ((ly2 - ly1) / (lx2 - lx1)) * (carpx - lx1) + ly1;
				
				rxc = ((rx2 - rx1) / (ry2 - ry1)) * (carpy - ry1) + rx1;
				ryc = ((ry2 - ry1) / (rx2 - rx1)) * (carpx - rx1) + ry1;
				
				if( carpx + r > lxc )
				{
					c = true;
					carpx = lxc - r;
				}
				else if( carpx - r < rxc )
				{
					c = true;
					carpx = rxc + r;
				}	
			}
			else if( curve_number == 4 )
			{
				lx1 = -size * ((track.left_r3 * -(float)Math.cos((-175.0f) * (float)Math.PI / 180.0f) + track.c3x) / p - 0.5f );
				ly1 = size * ((track.left_r3 * (float)Math.sin((-175.0f) * (float)Math.PI / 180.0f) + track.c3y) / p - 0.5f );

				rx1 = -size * ((track.right_r3 * -(float)Math.cos((-175.0f) * (float)Math.PI / 180.0f) + track.c3x) / p - 0.5f );
				ry1 = size * ((track.right_r3 * (float)Math.sin((-175.0f) * (float)Math.PI / 180.0f) + track.c3y) / p - 0.5f );

				lx2 = -size * ((track.left_r4 * -(float)Math.cos((5.0f) * (float)Math.PI / 180.0f) + track.c4x) / p - 0.5f );
				ly2 = size * ((track.left_r4 * (float)Math.sin((5.0f) * (float)Math.PI / 180.0f) + track.c4y) / p - 0.5f );

				rx2 = -size * ((track.right_r4 * -(float)Math.cos((5.0f) * (float)Math.PI / 180.0f) + track.c4x) / p - 0.5f );
				ry2 = size * ((track.right_r4 * (float)Math.sin((5.0f) * (float)Math.PI / 180.0f) + track.c4y) / p - 0.5f );
				
				lxc = ((lx2 - lx1) / (ly2 - ly1)) * (carpy - ly1) + lx1;
				rxc = ((rx2 - rx1) / (ry2 - ry1)) * (carpy - ry1) + rx1;
				
				if( carpx + r > rxc )
				{
					c = true;
					carpx = rxc - r;
				}
				else if( carpx - r < lxc )
				{
					c = true;
					carpx = lxc + r;
				}
				
				lyc = ((ly2 - ly1) / (lx2 - lx1)) * (carpx - lx1) + ly1;
				ryc = ((ry2 - ry1) / (rx2 - rx1)) * (carpx - rx1) + ry1;
				if( carpy + r > ryc )
				{
					c = true;
					carpy = ryc - r;
				}
				else if( carpy - r < lyc )
				{
					c = true;
					carpy = lyc + r;
				}	
			}
			else if( curve_number == 5 )
			{
				lx1 = -size * ((track.left_r4 * -(float)Math.cos((180.0f) * (float)Math.PI / 180.0f) + track.c4x) / p - 0.5f );
				ly1 = size * ((track.left_r4 * (float)Math.sin((180.0f) * (float)Math.PI / 180.0f) + track.c4y) / p - 0.5f );

				rx1 = -size * ((track.right_r4 * -(float)Math.cos((180.0f) * (float)Math.PI / 180.0f) + track.c4x) / p - 0.5f );
				ry1 = size * ((track.right_r4 * (float)Math.sin((180.0f) * (float)Math.PI / 180.0f) + track.c4y) / p - 0.5f );

				lx2 = -size * ((track.left_r5 * -(float)Math.cos((0.0f) * (float)Math.PI / 180.0f) + track.c5x) / p - 0.5f );
				ly2 = size * ((track.left_r5 * (float)Math.sin((0.0f) * (float)Math.PI / 180.0f) + track.c5y) / p - 0.5f );

				rx2 = -size * ((track.right_r5 * -(float)Math.cos((0.0f) * (float)Math.PI / 180.0f) + track.c5x) / p - 0.5f );
				ry2 = size * ((track.right_r5 * (float)Math.sin((0.0f) * (float)Math.PI / 180.0f) + track.c5y) / p - 0.5f );
				
				lxc = ((lx2 - lx1) / (ly2 - ly1)) * (carpy - ly1) + lx1;
				lyc = ((ly2 - ly1) / (lx2 - lx1)) * (carpx - lx1) + ly1;
				
				rxc = ((rx2 - rx1) / (ry2 - ry1)) * (carpy - ry1) + rx1;
				ryc = ((ry2 - ry1) / (rx2 - rx1)) * (carpx - rx1) + ry1;
				
				if( carpx + r > lxc )
				{
					c = true;
					carpx = lxc - r;
				}
				else if( carpx - r < rxc )
				{
					c = true;
					carpx = rxc + r;
				}	
			}
			else if( curve_number == 6 )
			{
				lx1 = -size * ((track.left_r5 * -(float)Math.cos((180.0f) * (float)Math.PI / 180.0f) + track.c5x) / p - 0.5f );
				ly1 = size * ((track.left_r5 * (float)Math.sin((180.0f) * (float)Math.PI / 180.0f) + track.c5y) / p - 0.5f );

				rx1 = -size * ((track.right_r5 * -(float)Math.cos((180.0f) * (float)Math.PI / 180.0f) + track.c5x) / p - 0.5f );
				ry1 = size * ((track.right_r5 * (float)Math.sin((180.0f) * (float)Math.PI / 180.0f) + track.c5y) / p - 0.5f );

				lx2 = -size * ((track.left_r6 * -(float)Math.cos((0.0f) * (float)Math.PI / 180.0f) + track.c6x) / p - 0.5f );
				ly2 = size * ((track.left_r6 * (float)Math.sin((0.0f) * (float)Math.PI / 180.0f) + track.c6y) / p - 0.5f );

				rx2 = -size * ((track.right_r6 * -(float)Math.cos((0.0f) * (float)Math.PI / 180.0f) + track.c6x) / p - 0.5f );
				ry2 = size * ((track.right_r6 * (float)Math.sin((0.0f) * (float)Math.PI / 180.0f) + track.c6y) / p - 0.5f );
				
				lxc = ((lx2 - lx1) / (ly2 - ly1)) * (carpy - ly1) + lx1;
				lyc = ((ly2 - ly1) / (lx2 - lx1)) * (carpx - lx1) + ly1;
				
				rxc = ((rx2 - rx1) / (ry2 - ry1)) * (carpy - ry1) + rx1;
				ryc = ((ry2 - ry1) / (rx2 - rx1)) * (carpx - rx1) + ry1;
				
				if( carpx + r > rxc )
				{
					c = true;
					carpx = rxc - r;
				}
				else if( carpx - r < lxc )
				{
					c = true;
					carpx = lxc + r;
				}	
			}
			else if( curve_number == 7 )
			{
				lx1 = -size * ((track.left_r6 * -(float)Math.cos((150.0f) * (float)Math.PI / 180.0f) + track.c6x) / p - 0.5f );
				ly1 = size * ((track.left_r6 * (float)Math.sin((150.0f) * (float)Math.PI / 180.0f) + track.c6y) / p - 0.5f );

				rx1 = -size * ((track.right_r6 * -(float)Math.cos((150.0f) * (float)Math.PI / 180.0f) + track.c6x) / p - 0.5f );
				ry1 = size * ((track.right_r6 * (float)Math.sin((150.0f) * (float)Math.PI / 180.0f) + track.c6y) / p - 0.5f );

				lx2 = -size * ((track.left_r7 * -(float)Math.cos((150.0f) * (float)Math.PI / 180.0f) + track.c7x) / p - 0.5f );
				ly2 = size * ((track.left_r7 * (float)Math.sin((150.0f) * (float)Math.PI / 180.0f) + track.c7y) / p - 0.5f );

				rx2 = -size * ((track.right_r7 * -(float)Math.cos((150.0f) * (float)Math.PI / 180.0f) + track.c7x) / p - 0.5f );
				ry2 = size * ((track.right_r7 * (float)Math.sin((150.0f) * (float)Math.PI / 180.0f) + track.c7y) / p - 0.5f );
				
				lxc = ((lx2 - lx1) / (ly2 - ly1)) * (carpy - ly1) + lx1;
				rxc = ((rx2 - rx1) / (ry2 - ry1)) * (carpy - ry1) + rx1;
				
				if( carpx + r > lxc )
				{
					c = true;
					carpx = lxc - r;
				}
				else if( carpx - r < rxc )
				{
					c = true;
					carpx = rxc + r;
				}

				lyc = ((ly2 - ly1) / (lx2 - lx1)) * (carpx - lx1) + ly1;
				ryc = ((ry2 - ry1) / (rx2 - rx1)) * (carpx - rx1) + ry1;
				if( carpy + r > ryc )
				{
					c = true;
					carpy = ryc - r;
				}
				else if( carpy - r < lyc )
				{
					c = true;
					carpy = lyc + r;
				}	
			}
		}
		
		if( c )
		{
			carspeed -= 1.8f;
			if( carspeed < 0.0f )
			{
				carspeed = 0.0f;
			}
		}
	}
	
	public void increase_lap()
	{
		if( carpx < 60.0f && carpx > -60.0f && lap_completed )
		{
			laps++;
			lap_completed = false;
		}
	}
}