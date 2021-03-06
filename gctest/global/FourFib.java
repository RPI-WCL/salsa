package gctest.global;

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


public class FourFib extends UniversalActor  {
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
		FourFib instance = (FourFib)new FourFib(uan, ual,null).construct();
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

	public static ActorReference getReferenceByName(UAN uan)	{ return new FourFib(false, uan); }
	public static ActorReference getReferenceByName(String uan)	{ return FourFib.getReferenceByName(new UAN(uan)); }
	public static ActorReference getReferenceByLocation(UAL ual)	{ return new FourFib(false, ual); }

	public static ActorReference getReferenceByLocation(String ual)	{ return FourFib.getReferenceByLocation(new UAL(ual)); }
	public FourFib(boolean o, UAN __uan)	{ super(false,__uan); }
	public FourFib(boolean o, UAL __ual)	{ super(false,__ual); }
	public FourFib(UAN __uan,UniversalActor.State sourceActor)	{ this(__uan, null, sourceActor); }
	public FourFib(UAL __ual,UniversalActor.State sourceActor)	{ this(null, __ual, sourceActor); }
	public FourFib(UniversalActor.State sourceActor)		{ this(null, null, sourceActor);  }
	public FourFib()		{  }
	public FourFib(UAN __uan, UAL __ual, Object obj) {
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
			    createRemotely(__uan, __ual, "gctest.global.FourFib", sourceRef);
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

	public UniversalActor construct (int _n) {
		Object[] __arguments = { new Integer(_n) };
		this.send( new Message(this, this, "construct", __arguments, null, null) );
		return this;
	}

	public UniversalActor construct() {
		Object[] __arguments = { };
		this.send( new Message(this, this, "construct", __arguments, null, null) );
		return this;
	}

	public class State extends UniversalActor .State {
		public FourFib self;
		public void updateSelf(ActorReference actorReference) {
			((FourFib)actorReference).setUAL(getUAL());
			((FourFib)actorReference).setUAN(getUAN());
			self = new FourFib(false,getUAL());
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
			addClassName( "gctest.global.FourFib$State" );
			addMethodsForClasses();
		}

		public void construct() {}

		public void process(Message message) {
			Method[] matches = getMatches(message.getMethodName());
			Object returnValue = null;
			Exception exception = null;

			if (matches != null) {
				if (!message.getMethodName().equals("die")) {activateArgsGC(message);}
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

		int n;
		void construct(int _n){
			n = _n;
		}
		public int add(int x, int y) {
			return x+y;
		}
		public int addAll(int a, int b, int c, int d) {
			return a+b+c+d;
		}
		public int fibfun(int nn) {
			if (nn==0) {{
				return 0;
			}
}			else {if (nn<=2) {{
				return 1;
			}
}			else {{
				return (this.fibfun(nn-1)+this.fibfun(nn-2));
			}
}}		}
		public int compute() {
			if (n==0) {{
				return 0;
			}
}			else {if (n<=2) {{
				return 1;
			}
}			else {if (n<=30) {{
				return (this.fibfun(n));
			}
}			else {{
				FourFib fib1 = ((FourFib)new FourFib(this).construct(n-1));
				FourFib fib2 = ((FourFib)new FourFib(this).construct(n-2));
				Token x = new Token("x");
				{
					// token x = fib1<-compute()
					{
						Object _arguments[] = {  };
						Message message = new Message( self, fib1, "compute", _arguments, null, x );
						__messages.add( message );
					}
				}
				Token y = new Token("y");
				{
					// token y = fib2<-compute()
					{
						Object _arguments[] = {  };
						Message message = new Message( self, fib2, "compute", _arguments, null, y );
						__messages.add( message );
					}
				}
				{
					// add(x, y)
					{
						Object _arguments[] = { x, y };
						Message message = new Message( self, self, "add", _arguments, null, currentMessage.getContinuationToken() );
						__messages.add( message );
					}
					throw new CurrentContinuationException();
				}
			}
}}}		}
		public void end(Object x) {
			System.out.println(x);
		}
		public void act(String args[]) {
			String[] hostArray = new String[4];
			try {
				n = Integer.parseInt(args[0]);
				String nameServer = args[1];
				for (int i = 0; i<4; i++){
					hostArray[i] = args[2+i];
				}
				FourFib fib1 = ((FourFib)new FourFib(new UAN("uan://"+nameServer+"/FHF/1"), new UAL("rmsp://"+hostArray[0]+"/FHF/1"),this).construct(n-2));
				FourFib fib2 = ((FourFib)new FourFib(new UAN("uan://"+nameServer+"/FHF/2"), new UAL("rmsp://"+hostArray[1]+"/FHF/2"),this).construct(n-3));
				FourFib fib3 = ((FourFib)new FourFib(new UAN("uan://"+nameServer+"/FHF/3"), new UAL("rmsp://"+hostArray[2]+"/FHF/3"),this).construct(n-3));
				FourFib fib4 = ((FourFib)new FourFib(new UAN("uan://"+nameServer+"/FHF/4"), new UAL("rmsp://"+hostArray[3]+"/FHF/4"),this).construct(n-4));
				Token a1 = new Token("a1");
				{
					// token a1 = fib1<-compute()
					{
						Object _arguments[] = {  };
						Message message = new Message( self, fib1, "compute", _arguments, null, a1 );
						__messages.add( message );
					}
				}
				Token a2 = new Token("a2");
				{
					// token a2 = fib2<-compute()
					{
						Object _arguments[] = {  };
						Message message = new Message( self, fib2, "compute", _arguments, null, a2 );
						__messages.add( message );
					}
				}
				Token a3 = new Token("a3");
				{
					// token a3 = fib3<-compute()
					{
						Object _arguments[] = {  };
						Message message = new Message( self, fib3, "compute", _arguments, null, a3 );
						__messages.add( message );
					}
				}
				Token a4 = new Token("a4");
				{
					// token a4 = fib4<-compute()
					{
						Object _arguments[] = {  };
						Message message = new Message( self, fib4, "compute", _arguments, null, a4 );
						__messages.add( message );
					}
				}
				{
					Token token_3_0 = new Token();
					// addAll(a1, a2, a3, a4)
					{
						Object _arguments[] = { a1, a2, a3, a4 };
						Message message = new Message( self, self, "addAll", _arguments, null, token_3_0 );
						__messages.add( message );
					}
					// ((FourFib)self)<-end(token)
					{
						Object _arguments[] = { token_3_0 };
						Message message = new Message( self, ((FourFib)self), "end", _arguments, token_3_0, null );
						__messages.add( message );
					}
				}
			}
			catch (Exception e) {
				System.err.println(e);
				System.err.println("Usage: java gctest.global.FourHostFib <number> <Name Server> <host1> <host2> <host3> <host4>");
				System.err.println("       where <Name Server> should be hostname:port");
				System.err.println("             <host> should be hostname:port");
			}

		}
	}
}