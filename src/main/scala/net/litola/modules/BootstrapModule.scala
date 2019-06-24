package net.litola.modules

import akka.actor.ActorSystem
import com.google.inject.{ AbstractModule, Provides, Singleton }

import scala.concurrent.ExecutionContext

class BootstrapModule extends AbstractModule {
  @Provides
  @Singleton
  def actorSystem: ActorSystem = ActorSystem("main")

  @Provides
  @Singleton
  def executionContext: ExecutionContext = scala.concurrent.ExecutionContext.global
}
