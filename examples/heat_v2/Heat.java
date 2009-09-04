// Import declarations generated by the SALSA compiler, do not modify.
import java.io.IOException;
import java.util.Vector;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.InvocationTargetException;

import salsa.language.Actor;
import salsa.language.ActorReference;
import salsa.language.Message;
import salsa.language.RunTime;
import salsa.language.ServiceFactory;
import gc.WeakReference;
import salsa.language.Token;
import salsa.language.exceptions.*;
import salsa.language.exceptions.CurrentContinuationException;

import salsa.language.UniversalActor;

import salsa.naming.UAN;
import salsa.naming.UAL;
import salsa.naming.MalformedUALException;
import salsa.naming.MalformedUANException;

import salsa.resources.SystemService;

import salsa.resources.ActorService;

// End SALSA compiler generated import delcarations.

import java.io.*;
import java.util.*;

public class Heat extends UniversalActor  {
	public static void main(String args[]) {
		UAN uan = null;
		UAL ual = null;
		if (System.getProperty("uan") != null) {
			uan = new UAN( System.getProperty("uan") );
			ServiceFactory.getTheater();
			RunTime.receivedUniversalActor();
		}
		if (System.getProperty("ual") != null) {
			ual = new UAL( System.getProperty("ual") );

			if (uan == null) {
				System.err.println("Actor Creation Error:");
				System.err.println("	uan: " + uan);
				System.err.println("	ual: " + ual);
				System.err.println("	Identifier: " + System.getProperty("identifier"));
				System.err.println("	Cannot specify an actor to have a ual at runtime without a uan.");
				System.err.println("	To give an actor a specific ual at runtime, use the identifier system property.");
				System.exit(0);
			}
			RunTime.receivedUniversalActor();
		}
		if (System.getProperty("identifier") != null) {
			if (ual != null) {
				System.err.println("Actor Creation Error:");
				System.err.println("	uan: " + uan);
				System.err.println("	ual: " + ual);
				System.err.println("	Identifier: " + System.getProperty("identifier"));
				System.err.println("	Cannot specify an identifier and a ual with system properties when creating an actor.");
				System.exit(0);
			}
			ual = new UAL( ServiceFactory.getTheater().getLocation() + System.getProperty("identifier"));
		}
		RunTime.receivedMessage();
		Heat instance = (Heat)new Heat(uan, ual,null).construct();
		gc.WeakReference instanceRef=new gc.WeakReference(uan,ual);
		{
			Object[] _arguments = { args };

			//preAct() for local actor creation
			//act() for remote actor creation
			if (ual != null && !ual.getLocation().equals(ServiceFactory.getTheater().getLocation())) {instance.send( new Message(instanceRef, instanceRef, "act", _arguments, false) );}
			else {instance.send( new Message(instanceRef, instanceRef, "preAct", _arguments, false) );}
		}
		RunTime.finishedProcessingMessage();
	}

	public static ActorReference getReferenceByName(UAN uan)	{ return new Heat(false, uan); }
	public static ActorReference getReferenceByName(String uan)	{ return Heat.getReferenceByName(new UAN(uan)); }
	public static ActorReference getReferenceByLocation(UAL ual)	{ return new Heat(false, ual); }

	public static ActorReference getReferenceByLocation(String ual)	{ return Heat.getReferenceByLocation(new UAL(ual)); }
	public Heat(boolean o, UAN __uan)	{ super(false,__uan); }
	public Heat(boolean o, UAL __ual)	{ super(false,__ual); }
	public Heat(UAN __uan,UniversalActor.State sourceActor)	{ this(__uan, null, sourceActor); }
	public Heat(UAL __ual,UniversalActor.State sourceActor)	{ this(null, __ual, sourceActor); }
	public Heat(UniversalActor.State sourceActor)		{ this(null, null, sourceActor);  }
	public Heat()		{  }
	public Heat(UAN __uan, UAL __ual, Object obj) {
		//decide the type of sourceActor
		//if obj is null, the actor must be the startup actor.
		//if obj is an actorReference, this actor is created by a remote actor

		if (obj instanceof UniversalActor.State || obj==null) {
			  UniversalActor.State sourceActor;
			  if (obj!=null) { sourceActor=(UniversalActor.State) obj;}
			  else {sourceActor=null;}

			  //remote creation message sent to a remote system service.
			  if (__ual != null && !__ual.getLocation().equals(ServiceFactory.getTheater().getLocation())) {
			    WeakReference sourceRef;
			    if (sourceActor!=null && sourceActor.getUAL() != null) {sourceRef = new WeakReference(sourceActor.getUAN(),sourceActor.getUAL());}
			    else {sourceRef = null;}
			    if (sourceActor != null) {
			      if (__uan != null) {sourceActor.getActorMemory().getForwardList().putReference(__uan);}
			      else if (__ual!=null) {sourceActor.getActorMemory().getForwardList().putReference(__ual);}

			      //update the source of this actor reference
			      setSource(sourceActor.getUAN(), sourceActor.getUAL());
			      activateGC();
			    }
			    createRemotely(__uan, __ual, "Heat", sourceRef);
			  }

			  // local creation
			  else {
			    State state = new State(__uan, __ual);

			    //assume the reference is weak
			    muteGC();

			    //the source actor is  the startup actor
			    if (sourceActor == null) {
			      state.getActorMemory().getInverseList().putInverseReference("rmsp://me");
			    }

			    //the souce actor is a normal actor
			    else if (sourceActor instanceof UniversalActor.State) {

			      // this reference is part of garbage collection
			      activateGC();

			      //update the source of this actor reference
			      setSource(sourceActor.getUAN(), sourceActor.getUAL());

			      /* Garbage collection registration:
			       * register 'this reference' in sourceActor's forward list @
			       * register 'this reference' in the forward acquaintance's inverse list
			       */
			      String inverseRefString=null;
			      if (sourceActor.getUAN()!=null) {inverseRefString=sourceActor.getUAN().toString();}
			      else if (sourceActor.getUAL()!=null) {inverseRefString=sourceActor.getUAL().toString();}
			      if (__uan != null) {sourceActor.getActorMemory().getForwardList().putReference(__uan);}
			      else if (__ual != null) {sourceActor.getActorMemory().getForwardList().putReference(__ual);}
			      else {sourceActor.getActorMemory().getForwardList().putReference(state.getUAL());}

			      //put the inverse reference information in the actormemory
			      if (inverseRefString!=null) state.getActorMemory().getInverseList().putInverseReference(inverseRefString);
			    }
			    state.updateSelf(this);
			    ServiceFactory.getNaming().setEntry(state.getUAN(), state.getUAL(), state);
			    if (getUAN() != null) ServiceFactory.getNaming().update(state.getUAN(), state.getUAL());
			  }
		}

		//creation invoked by a remote message
		else if (obj instanceof ActorReference) {
			  ActorReference sourceRef= (ActorReference) obj;
			  State state = new State(__uan, __ual);
			  muteGC();
			  state.getActorMemory().getInverseList().putInverseReference("rmsp://me");
			  if (sourceRef.getUAN() != null) {state.getActorMemory().getInverseList().putInverseReference(sourceRef.getUAN());}
			  else if (sourceRef.getUAL() != null) {state.getActorMemory().getInverseList().putInverseReference(sourceRef.getUAL());}
			  state.updateSelf(this);
			  ServiceFactory.getNaming().setEntry(state.getUAN(), state.getUAL(),state);
			  if (getUAN() != null) ServiceFactory.getNaming().update(state.getUAN(), state.getUAL());
		}
	}

	public UniversalActor construct() {
		Object[] __arguments = { };
		this.send( new Message(this, this, "construct", __arguments, null, null) );
		return this;
	}

	public class State extends UniversalActor .State {
		public Heat self;
		public void updateSelf(ActorReference actorReference) {
			((Heat)actorReference).setUAL(getUAL());
			((Heat)actorReference).setUAN(getUAN());
			self = new Heat(false,getUAL());
			self.setUAN(getUAN());
			self.setUAL(getUAL());
			self.activateGC();
		}

		public void preAct(String[] arguments) {
			getActorMemory().getInverseList().removeInverseReference("rmsp://me",1);
			{
				Object[] __args={arguments};
				self.send( new Message(self,self, "act", __args, null,null,false) );
			}
		}

		public State() {
			this(null, null);
		}

		public State(UAN __uan, UAL __ual) {
			super(__uan, __ual);
			addClassName( "Heat$State" );
			addMethodsForClasses();
		}

		public void construct() {}

		public void process(Message message) {
			Method[] matches = getMatches(message.getMethodName());
			Object returnValue = null;
			Exception exception = null;

			if (matches != null) {
				if (!message.getMethodName().equals("die")) {message.activateArgsGC(this);}
				for (int i = 0; i < matches.length; i++) {
					try {
						if (matches[i].getParameterTypes().length != message.getArguments().length) continue;
						returnValue = matches[i].invoke(this, message.getArguments());
					} catch (Exception e) {
						if (e.getCause() instanceof CurrentContinuationException) {
							sendGeneratedMessages();
							return;
						} else if (e instanceof InvocationTargetException) {
							sendGeneratedMessages();
							exception = (Exception)e.getCause();
							break;
						} else {
							continue;
						}
					}
					sendGeneratedMessages();
					currentMessage.resolveContinuations(returnValue);
					return;
				}
			}

			System.err.println("Message processing exception:");
			if (message.getSource() != null) {
				System.err.println("\tSent by: " + message.getSource().toString());
			} else System.err.println("\tSent by: unknown");
			System.err.println("\tReceived by actor: " + toString());
			System.err.println("\tMessage: " + message.toString());
			if (exception == null) {
				if (matches == null) {
					System.err.println("\tNo methods with the same name found.");
					return;
				}
				System.err.println("\tDid not match any of the following: ");
				for (int i = 0; i < matches.length; i++) {
					System.err.print("\t\tMethod: " + matches[i].getName() + "( ");
					Class[] parTypes = matches[i].getParameterTypes();
					for (int j = 0; j < parTypes.length; j++) {
						System.err.print(parTypes[j].getName() + " ");
					}
					System.err.println(")");
				}
			} else {
				System.err.println("\tThrew exception: " + exception);
				exception.printStackTrace();
			}
		}

		int iterations;
		int x;
		int y;
		int number_actors;
		long initialTime;
		String filename;
		Collector collector;
		HeatWorker[] workers;
		public void produceFile(LowMemoryArray data) {
			try {
				PrintWriter out = new PrintWriter(new FileWriter(filename));
				out.println(x+" "+y);
				for (int i = 0; i<x; i++){
					for (int j = 0; j<y; j++){
						try {
							out.println(data.get(i, j));
						}
						catch (Exception e) {
continue;						}

					}
				}
				out.flush();
				out.close();
			}
			catch (IOException ioe) {
				{
					// standardOutput<-println("[error] Can't open the file "+filename+" for writing.")
					{
						Object _arguments[] = { "[error] Can't open the file "+filename+" for writing." };
						Message message = new Message( self, standardOutput, "println", _arguments, null, null );
						__messages.add( message );
					}
				}
			}

		}
		public void doWork(int iterations, String[] theaters, String name_server) {
			int rowsPerActor = x/number_actors;
			workers = new HeatWorker[number_actors];
			collector = ((Collector)new Collector(this).construct(number_actors, iterations));
			if (System.getProperty("random")!=null) {{
				java.util.Random rand = new java.util.Random(13);
				for (int i = 0; i<number_actors; i++){
					int target_theater = rand.nextInt(theaters.length-1);
					System.err.println("Creating worker: "+i+" at: "+theaters[target_theater]+"heatworker_"+i);
					workers[i] = ((HeatWorker)new HeatWorker(new UAN(name_server+"heatworker_"+i), new UAL(theaters[target_theater]+"heatworker_"+i),this).construct(rowsPerActor, y, iterations, ((Heat)self), String.valueOf(i), collector));
				}
			}
}			else {if (theaters!=null) {{
				int a = 0;
				double actors_per_theater = (double)number_actors/(double)theaters.length;
				for (int i = 0; i<theaters.length; i++){
					for (int j = (int)java.lang.Math.round(i*actors_per_theater); j<java.lang.Math.round((i+1)*actors_per_theater); j++){
						System.err.println("Creating worker: "+a+" at: "+theaters[i]+"heatworker_"+a);
						workers[a] = ((HeatWorker)new HeatWorker(new UAN(name_server+"heatworker_"+a), new UAL(theaters[i]+"heatworker_"+a),this).construct(rowsPerActor, y, iterations, ((Heat)self), String.valueOf(a), collector));
						a++;
					}
				}
			}
}			else {{
				for (int a = 0; a<number_actors; a++){
					workers[a] = ((HeatWorker)new HeatWorker(this).construct(rowsPerActor, y, iterations, ((Heat)self), String.valueOf(a), collector));
				}
			}
}}			if (number_actors>1) {{
				{
					Token token_3_0 = new Token();
					Token token_3_1 = new Token();
					// standardOutput<-println("Connecting worker neighbors")
					{
						Object _arguments[] = { "Connecting worker neighbors" };
						Message message = new Message( self, standardOutput, "println", _arguments, null, token_3_0 );
						__messages.add( message );
					}
					// join block
					token_3_1.setJoinDirector();
					for (int a = 0; a<number_actors; a++){
						if (a>0) {						{
							// workers[a]<-connectTop(workers[a-1])
							{
								Object _arguments[] = { workers[a-1] };
								Message message = new Message( self, workers[a], "connectTop", _arguments, token_3_0, token_3_1 );
								__messages.add( message );
							}
						}
}						if (a<number_actors-1) {						{
							// workers[a]<-connectBottom(workers[a+1])
							{
								Object _arguments[] = { workers[a+1] };
								Message message = new Message( self, workers[a], "connectBottom", _arguments, token_3_0, token_3_1 );
								__messages.add( message );
							}
						}
}					}
					addJoinToken(token_3_1);
					// doWork2()
					{
						Object _arguments[] = {  };
						Message message = new Message( self, self, "doWork2", _arguments, token_3_1, null );
						__messages.add( message );
					}
				}
			}
}			else {{
				{
					// doWork2()
					{
						Object _arguments[] = {  };
						Message message = new Message( self, self, "doWork2", _arguments, null, null );
						__messages.add( message );
					}
				}
			}
}		}
		public void doWork2() {
			for (int i = 0; i<number_actors; i++){
				{
					// workers[i]<-startWork()
					{
						Object _arguments[] = {  };
						Message message = new Message( self, workers[i], "startWork", _arguments, null, null );
						__messages.add( message );
					}
				}
			}
			initialTime = System.currentTimeMillis();
			{
				// standardOutput<-println("Starting the computation at: "+initialTime)
				{
					Object _arguments[] = { "Starting the computation at: "+initialTime };
					Message message = new Message( self, standardOutput, "println", _arguments, null, null );
					__messages.add( message );
				}
			}
		}
		Vector results = new Vector();
		int work_received = 0;
		public void workFinished() {
			work_received++;
			if (work_received==number_actors) {{
				{
					Token token_3_0 = new Token();
					Token token_3_1 = new Token();
					// collector<-printTimes()
					{
						Object _arguments[] = {  };
						Message message = new Message( self, collector, "printTimes", _arguments, null, token_3_0 );
						__messages.add( message );
					}
					// endTimer()
					{
						Object _arguments[] = {  };
						Message message = new Message( self, self, "endTimer", _arguments, token_3_0, token_3_1 );
						__messages.add( message );
					}
					// standardOutput<-println("Computation is done, received work from: "+work_received+" actors!")
					{
						Object _arguments[] = { "Computation is done, received work from: "+work_received+" actors!" };
						Message message = new Message( self, standardOutput, "println", _arguments, token_3_1, null );
						__messages.add( message );
					}
				}
			}
}		}
		public void workFinished(LowMemoryArray work) {
			results.add(work);
			work_received++;
			if (work_received==number_actors) {{
				{
					Token token_3_0 = new Token();
					Token token_3_1 = new Token();
					Token token_3_2 = new Token();
					// endTimer()
					{
						Object _arguments[] = {  };
						Message message = new Message( self, self, "endTimer", _arguments, null, token_3_0 );
						__messages.add( message );
					}
					// composeMatrix()
					{
						Object _arguments[] = {  };
						Message message = new Message( self, self, "composeMatrix", _arguments, token_3_0, token_3_1 );
						__messages.add( message );
					}
					// produceFile(token)
					{
						Object _arguments[] = { token_3_1 };
						Message message = new Message( self, self, "produceFile", _arguments, token_3_1, token_3_2 );
						__messages.add( message );
					}
					// standardOutput<-println("Computation is done, received work from: "+work_received+" actors!")
					{
						Object _arguments[] = { "Computation is done, received work from: "+work_received+" actors!" };
						Message message = new Message( self, standardOutput, "println", _arguments, token_3_2, null );
						__messages.add( message );
					}
				}
			}
}		}
		public void actor_split() {
			System.err.println("Farmer received split!");
			number_actors++;
		}
		public void actor_merged() {
			System.err.println("Farmer received merge!");
			number_actors--;
		}
		public void endTimer() {
			long finalTime = System.currentTimeMillis();
			long runningTime = finalTime-initialTime;
			double processingRate = 1.0*iterations*(x-2)*(y-2)/runningTime;
			System.out.println("Time for "+iterations+" iterations on "+x+","+y+" grid: "+runningTime+"ms.");
			System.out.println("number of workers: "+number_actors);
		}
		public LowMemoryArray composeMatrix() {
			LowMemoryArray cresults;
			LowMemoryArray m = new LowMemoryArray(x, y);
			int i = 0;
			for (int a = 0; a<results.size(); a++){
				cresults = (LowMemoryArray)results.get(a);
				for (int j = 0; j<cresults.x; j++){
					for (int k = 0; k<y; k++){
						m.set((cresults.y*a)+j, k, cresults.get(j, k));
					}
					i++;
				}
			}
			{
				// standardOutput<-println("Returned matrix size:"+m.x+","+m.y)
				{
					Object _arguments[] = { "Returned matrix size:"+m.x+","+m.y };
					Message message = new Message( self, standardOutput, "println", _arguments, null, null );
					__messages.add( message );
				}
			}
			return m;
		}
		public void act(String args[]) {
			if (args.length!=7&&args.length!=5) {{
				System.err.println("Incorrect arguments.");
				System.err.println("Usage:");
				System.err.println("java heat.Heat [<theaters_file> <name_server>] <iterations> <x> <y> <number of actors> <filename>");
				System.exit(0);
			}
}			String[] theaters = null;
			String name_server = null;
			int i = 0;
			if (args.length==7) {{
				String theater_file = args[0];
				try {
					BufferedReader in = new BufferedReader(new FileReader(theater_file));
					String line = in.readLine();
					Vector theaters_vector = new Vector();
					while (line!=null) {
						theaters_vector.add(line);
						line = in.readLine();
					}
					theaters = new String[theaters_vector.size()];
					for (int j = 0; j<theaters_vector.size(); j++){
						theaters[j] = "rmsp://"+(String)theaters_vector.get(j)+"/";
					}
				}
				catch (Exception e) {
					System.err.println("Error reading theaters file: "+e);
					e.printStackTrace();
				}

				name_server = args[1];
				i = 2;
			}
}			iterations = Integer.parseInt(args[i]);
			i++;
			x = Integer.parseInt(args[i]);
			i++;
			y = Integer.parseInt(args[i]);
			i++;
			number_actors = Integer.parseInt(args[i]);
			i++;
			filename = args[i];
			x += 2;
			y += 2;
			{
				// doWork(new Integer(iterations), theaters, name_server)
				{
					Object _arguments[] = { new Integer(iterations), theaters, name_server };
					Message message = new Message( self, self, "doWork", _arguments, null, null );
					__messages.add( message );
				}
			}
			{
				// ((Heat)self)<-keepAlive()
				{
					Object _arguments[] = {  };
					Message message = new Message( self, ((Heat)self), "keepAlive", _arguments, null, null );
					Object[] _propertyInfo = { new Integer(60000) };
					message.setProperty( "delay", _propertyInfo );
					__messages.add( message );
				}
			}
		}
		public void keepAlive() {
			System.out.println("Keep alive!");
			{
				// ((Heat)self)<-keepAlive()
				{
					Object _arguments[] = {  };
					Message message = new Message( self, ((Heat)self), "keepAlive", _arguments, null, null );
					Object[] _propertyInfo = { new Integer(60000) };
					message.setProperty( "delay", _propertyInfo );
					__messages.add( message );
				}
			}
		}
	}
}