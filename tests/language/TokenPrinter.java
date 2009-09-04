package tests.language;

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


public class TokenPrinter extends UniversalActor  {
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
		TokenPrinter instance = (TokenPrinter)new TokenPrinter(uan, ual,null).construct();
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

	public static ActorReference getReferenceByName(UAN uan)	{ return new TokenPrinter(false, uan); }
	public static ActorReference getReferenceByName(String uan)	{ return TokenPrinter.getReferenceByName(new UAN(uan)); }
	public static ActorReference getReferenceByLocation(UAL ual)	{ return new TokenPrinter(false, ual); }

	public static ActorReference getReferenceByLocation(String ual)	{ return TokenPrinter.getReferenceByLocation(new UAL(ual)); }
	public TokenPrinter(boolean o, UAN __uan)	{ super(false,__uan); }
	public TokenPrinter(boolean o, UAL __ual)	{ super(false,__ual); }
	public TokenPrinter(UAN __uan,UniversalActor.State sourceActor)	{ this(__uan, null, sourceActor); }
	public TokenPrinter(UAL __ual,UniversalActor.State sourceActor)	{ this(null, __ual, sourceActor); }
	public TokenPrinter(UniversalActor.State sourceActor)		{ this(null, null, sourceActor);  }
	public TokenPrinter()		{  }
	public TokenPrinter(UAN __uan, UAL __ual, Object obj) {
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
			    createRemotely(__uan, __ual, "tests.language.TokenPrinter", sourceRef);
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
		public TokenPrinter self;
		public void updateSelf(ActorReference actorReference) {
			((TokenPrinter)actorReference).setUAL(getUAL());
			((TokenPrinter)actorReference).setUAN(getUAN());
			self = new TokenPrinter(false,getUAL());
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
			addClassName( "tests.language.TokenPrinter$State" );
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

		public int compute() {
			return 5;
		}
		public String computeString() {
			return "a string";
		}
		public Object computeObject() {
			return new Integer(5);
		}
		public void print(int number) {
			{
				// standardOutput<-println(new Integer(number+2))
				{
					Object _arguments[] = { new Integer(number+2) };
					Message message = new Message( self, standardOutput, "println", _arguments, null, null );
					__messages.add( message );
				}
			}
		}
		public void print(Integer number) {
			{
				// standardOutput<-println(number)
				{
					Object _arguments[] = { number };
					Message message = new Message( self, standardOutput, "println", _arguments, null, null );
					__messages.add( message );
				}
			}
		}
		public void print(String string) {
			{
				// standardOutput<-println(string+" indeed.")
				{
					Object _arguments[] = { string+" indeed." };
					Message message = new Message( self, standardOutput, "println", _arguments, null, null );
					__messages.add( message );
				}
			}
		}
		public void print(Object object) {
			{
				// standardOutput<-println("Object:"+object)
				{
					Object _arguments[] = { "Object:"+object };
					Message message = new Message( self, standardOutput, "println", _arguments, null, null );
					__messages.add( message );
				}
			}
		}
		public void act(String[] args) {
			{
				Token token_2_0 = new Token();
				// compute()
				{
					Object _arguments[] = {  };
					Message message = new Message( self, self, "compute", _arguments, null, token_2_0 );
					__messages.add( message );
				}
				// print(token)
				{
					Object _arguments[] = { token_2_0 };
					Message message = new Message( self, self, "print", _arguments, token_2_0, null );
					__messages.add( message );
				}
			}
			{
				Token token_2_0 = new Token();
				// computeString()
				{
					Object _arguments[] = {  };
					Message message = new Message( self, self, "computeString", _arguments, null, token_2_0 );
					__messages.add( message );
				}
				// print(token)
				{
					Object _arguments[] = { token_2_0 };
					Message message = new Message( self, self, "print", _arguments, token_2_0, null );
					__messages.add( message );
				}
			}
			{
				Token token_2_0 = new Token();
				// computeObject()
				{
					Object _arguments[] = {  };
					Message message = new Message( self, self, "computeObject", _arguments, null, token_2_0 );
					__messages.add( message );
				}
				// print(token)
				{
					Object _arguments[] = { token_2_0 };
					Message message = new Message( self, self, "print", _arguments, token_2_0, null );
					__messages.add( message );
				}
			}
			int i = 0;
			String s = "s";
			{
				Token token_2_0 = new Token();
				Token token_2_1 = new Token();
				Token token_2_2 = new Token();
				Token token_2_3 = new Token();
				// print(1)
				{
					Object _arguments[] = { new Integer(1) };
					Message message = new Message( self, self, "print", _arguments, null, token_2_0 );
					__messages.add( message );
				}
				// print(s)
				{
					Object _arguments[] = { s };
					Message message = new Message( self, self, "print", _arguments, token_2_0, token_2_1 );
					__messages.add( message );
				}
				// print("a"+"b")
				{
					Object _arguments[] = { "a"+"b" };
					Message message = new Message( self, self, "print", _arguments, token_2_1, token_2_2 );
					__messages.add( message );
				}
				// print(new Integer(1))
				{
					Object _arguments[] = { new Integer(1) };
					Message message = new Message( self, self, "print", _arguments, token_2_2, token_2_3 );
					__messages.add( message );
				}
				// print(new Integer(1+1))
				{
					Object _arguments[] = { new Integer(1+1) };
					Message message = new Message( self, self, "print", _arguments, token_2_3, null );
					__messages.add( message );
				}
			}
		}
	}
}