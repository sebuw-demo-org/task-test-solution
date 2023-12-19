module Dreadbury

abstract sig Person {
  killed: set Person,
  hates: set Person,
  richer: set Person
}

one sig Agatha, Butler, Charles extends Person {}

pred nativeEncoding() {
  some killed.Agatha
  killed in hates and no (killed & richer)
  no (Charles.hates & Agatha.hates)
  (Person - Butler) in Agatha.hates
  (Person - richer.Agatha) in Butler.hates
  Agatha.hates in Butler.hates
  all x : Person | Person != x.hates
}

run nativeEncoding for 3